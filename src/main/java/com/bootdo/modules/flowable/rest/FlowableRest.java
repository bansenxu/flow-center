package com.bootdo.modules.flowable.rest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.modules.flowable.domain.DeModel;
import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.service.DeModelService;
import com.bootdo.modules.flowable.service.ExtDatasourceService;
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
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	//
	public String startFlow(@RequestHeader(value="Authorization") String Authorization,@RequestBody String json) {
		String jwts = Authorization;
		String[] res = jwts.split(".");
		jwts = jwts.substring(7);
		String header = jwts.substring(0, jwts.indexOf("."));
		jwts = jwts.substring(jwts.indexOf(".") + 1);
		String payload = jwts.substring(0, jwts.indexOf("."));
		String sign = jwts.substring(jwts.indexOf(".") + 1);

		System.out.println(new String(Base64.getDecoder().decode(header)));
		System.out.println(new String(Base64.getDecoder().decode(payload)));
		
		//获取流程名称
		JSONObject jsonObject = (JSONObject) JSON.parse(json);
		String flowName = jsonObject.getString("flowname");
		System.out.println("flowName====="+flowName);
		
		//查询流程部署json
		DeModel b = deModelService.get(flowName);
		System.out.println(b.getModelEditerJson());
		ExtModelEditor editorModel = TransUtil.transExtModelEditor(b.getModelEditerJson());
		
		//前端传入的参数
		String paramString = jsonObject.getString("param");
		System.out.println("param====="+paramString);
		HashMap<String, Object> map = JSON.parseObject(paramString,HashMap.class);
		map.put("flowName", flowName);
		map.put("model", editorModel);
		
		map.put("header", "header:i am header");
		map.put("body", "i am body");
		//流程参数构建
		//数据源
		map = setDSFromModel(editorModel,map);

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowName, map);
		return "提交成功.流程Id为：" + processInstance.getId();
		
	}
	
	public static void main(String[] ss)
	{
		String jwts = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmYmZjMDlmMS1lNzg2LTQ1ZGUtOTY3NS05NTA2NTdhODIxM2EifQ.eyJqdGkiOiJhNjdlZTIyNC0xN2RhLTQ3YTEtYjg4NS1hYTg0YWY2MWFmNWMiLCJleHAiOjE1NjgyOTU5NTUsIm5iZiI6MCwiaWF0IjoxNTY4MjU5OTU1LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvd29ya2Zsb3ciLCJzdWIiOiI4ZDJjNDFmNC1mNDUwLTQ2YjItOGEzZS1kNWQ1NzgzNjg1ZWIiLCJ0eXAiOiJTZXJpYWxpemVkLUlEIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOGE2NWNhZTYtOWRlMS00ZjM2LWE5Y2QtMDI0NDQzZjIxMmNhIiwic3RhdGVfY2hlY2tlciI6IkNMbGxqTlM2NmxxZDdnZmJCWUY4MUpLbXp1dTVNajNaRE02NDJTV2dUeEkifQ.L0Ugq5R9boxNOW8XUNW0mabUkyg1K_vPHOWuB0XPNL0";
		jwts = jwts.substring(7);
		System.out.println(jwts);
		String[] res = jwts.split(".");

		String header = jwts.substring(0, jwts.indexOf("."));
		jwts = jwts.substring(jwts.indexOf(".") + 1);
		String payload = jwts.substring(0, jwts.indexOf("."));
		String sign = jwts.substring(jwts.indexOf(".") + 1);

		System.out.println(new String(Base64.getDecoder().decode(header)));
		System.out.println(new String(Base64.getDecoder().decode(payload)));
		
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
                .setJdbcUsername("root")
                .setJdbcPassword("123")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    	ProcessEngine processEngine = cfg.buildProcessEngine();
    	RuntimeService runtimeService = processEngine.getRuntimeService();
    	
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("name", "wangxing");
    	
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test",variables);
		System.out.println("提交成功.流程Id为：" + processInstance.getId()); 
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
