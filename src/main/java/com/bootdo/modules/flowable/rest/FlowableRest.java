package com.bootdo.modules.flowable.rest;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.interfaces.RSAKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;
import org.keycloak.common.util.PemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bootdo.modules.flowable.domain.DeModel;
import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.service.DeModelService;
import com.bootdo.modules.flowable.service.ExtDatasourceService;
import com.bootdo.modules.flowable.service.PulishKeyService;
import com.bootdo.modules.flowable.utils.TransUtil;

/*
@RestController 标明此Controller提供RestAPI
@RequestMapping及其变体。映射http请求url到java方法
@RequestParam 映射请求参数到java方法的参数
@PageableDefault 指定分页参数默认值
@PathVariable 映射url片段到java方法的参数
在url声明中使用正则表达式
@JsonView控制json输出内容
*/
@RestController
@RequestMapping("/flowable")
public class FlowableRest {
	
	@Autowired
    private RuntimeService runtimeService;
	
	@Autowired
	private DeModelService deModelService;
	
	@Autowired
	private ExtDatasourceService extDatasourceService;
	
	@Autowired
	private PulishKeyService pulishKeyService;
	
	private static Logger logger = LogManager.getLogger(FlowableRest.class.getName());
	
//	@Value("${keycloak.publicKey}")private String publicKey;
	
	@CrossOrigin
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	//
	public String startFlow(@RequestHeader(value="Authorization") String Authorization,@RequestBody String json)throws Exception {
//		String jwts = Authorization;
//		String[] res = jwts.split(".");
//		jwts = jwts.substring(7);
//		String header = jwts.substring(0, jwts.indexOf("."));
//		jwts = jwts.substring(jwts.indexOf(".") + 1);
//		String payload = jwts.substring(0, jwts.indexOf("."));
//		String sign = jwts.substring(jwts.indexOf(".") + 1);

//		System.out.println(new String(Base64.getDecoder().decode(header)));
//		System.out.println(new String(Base64.getDecoder().decode(payload)));
		
		//获取流程名称
		logger.debug("Authorization====="+Authorization);
		logger.debug("json====="+json);
		
//		json = URLDecoder.decode(json);
//		json = json.substring(0,json.length()-1);
//		System.out.println("decode=json====="+json);
		
		//解析请求体json字符串
		JSONObject jsonObject = (JSONObject) JSON.parse(json);
		String flowName = jsonObject.getString("flowname");
		String username = jsonObject.getString("username");
		if(flowName==null || flowName.equals(""))
		{
			logger.error("FC-9000 flow name can not be null.");
			throw new Exception("FC-9000 flow name can not be null.");
		}
		if(username==null || username.equals(""))
		{
			logger.error("FC-9001 username name can not be null.");
			throw new Exception("FC-9001 username name can not be null.");
		}
		
		logger.debug("flowName====="+flowName);
		logger.debug("username====="+username);
		
		//验证JWT start
		try
        {
			String publicKey = pulishKeyService.getByUserName(username).getPulishKey();
			System.out.println("publicKey====="+publicKey);
        	String[] parts = Authorization.split("\\.");
            String headerJson = new String(Base64.getDecoder().decode(parts[0]));
            JWT.require(Algorithm.RSA256((RSAKey) PemUtils.decodePublicKey(publicKey)))
            .build().verify(Authorization);
        }catch(Exception e)
        {
        	String error = e.getMessage();
        	if(error.indexOf("The Token has expired on")>-1)
        	{
        		logger.error("FC-9003 token expired");
        		throw new Exception("FC-9003 token expired");
        	}else{
        		logger.error(e);
        		throw e;
        	}
        }
		//验证JWT end
		
		//查询流程部署json
		DeModel b = deModelService.get(flowName);
		if(b==null)
		{
			logger.error("FC-9004 can not found the "+ flowName +" deploy model.");
			throw new Exception("FC-9004 can not found the "+ flowName +" deploy model.");
		}
		logger.debug("modelEditerJson====="+b.getModelEditerJson());
		ExtModelEditor editorModel = TransUtil.transExtModelEditor(b.getModelEditerJson());
		
		//前端传入的参数转换为流程参数，并加入流程可能需要的外部参数
		String paramString = jsonObject.getString("param");
		logger.debug("param====="+paramString);
		HashMap<String, Object> map = JSON.parseObject(paramString,HashMap.class);
		map.put("flowName", flowName);
		map.put("model", editorModel);
		map.put("bearer",Authorization);
		map.put("username",username);
		
		//流程参数构建
		//数据源
		map = setDSFromModel(editorModel,map);

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowName, map);
		return "提交成功.流程Id为：" + processInstance.getId();
		
	}
	
	public static void main(String[] ss)
	{
//		String jwts = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmYmZjMDlmMS1lNzg2LTQ1ZGUtOTY3NS05NTA2NTdhODIxM2EifQ.eyJqdGkiOiJhNjdlZTIyNC0xN2RhLTQ3YTEtYjg4NS1hYTg0YWY2MWFmNWMiLCJleHAiOjE1NjgyOTU5NTUsIm5iZiI6MCwiaWF0IjoxNTY4MjU5OTU1LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvd29ya2Zsb3ciLCJzdWIiOiI4ZDJjNDFmNC1mNDUwLTQ2YjItOGEzZS1kNWQ1NzgzNjg1ZWIiLCJ0eXAiOiJTZXJpYWxpemVkLUlEIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOGE2NWNhZTYtOWRlMS00ZjM2LWE5Y2QtMDI0NDQzZjIxMmNhIiwic3RhdGVfY2hlY2tlciI6IkNMbGxqTlM2NmxxZDdnZmJCWUY4MUpLbXp1dTVNajNaRE02NDJTV2dUeEkifQ.L0Ugq5R9boxNOW8XUNW0mabUkyg1K_vPHOWuB0XPNL0";
//		jwts = jwts.substring(7);
//		System.out.println(jwts);
//		String[] res = jwts.split(".");
//
//		String header = jwts.substring(0, jwts.indexOf("."));
//		jwts = jwts.substring(jwts.indexOf(".") + 1);
//		String payload = jwts.substring(0, jwts.indexOf("."));
//		String sign = jwts.substring(jwts.indexOf(".") + 1);
//
//		System.out.println(new String(Base64.getDecoder().decode(header)));
//		System.out.println(new String(Base64.getDecoder().decode(payload)));
//		
//		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
//                .setJdbcUsername("root")
//                .setJdbcPassword("123")
//                .setJdbcDriver("com.mysql.jdbc.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//
//    	ProcessEngine processEngine = cfg.buildProcessEngine();
//    	RuntimeService runtimeService = processEngine.getRuntimeService();
//    	
//    	Map<String, Object> variables = new HashMap<String, Object>();
//    	variables.put("name", "wangxing");
//    	
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test",variables);
//		System.out.println("提交成功.流程Id为：" + processInstance.getId()); 
	}
	
	/*从json中获取数据源*/
	public HashMap<String, Object> setDSFromModel(ExtModelEditor editorModel,HashMap<String, Object> map)
	{
		String dataSource_Id = null;
		for(ExtChildNode tem:editorModel.getChildShapes())
		{
			if(tem!=null){
				if(tem.getProperties()!=null)
				{
					if(tem.getProperties().getServicetaskdelegateexpression()!=null)
					{
						dataSource_Id = tem.getProperties().getServicetaskdelegateexpression();
						ExtDatasourceDO ds =extDatasourceService.get(dataSource_Id);
						System.out.println(tem.getProperties().getName()+":"+tem.getProperties().getOverrideid());
						map.put(tem.getProperties().getOverrideid()+"-ds", ds);
					}
				}
			}
		}
		return map;
	}

}
