package com.bootdo.kekcloak.service;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.kekcloak.TokenUtil;
import com.bootdo.modules.kekcloak.RequestCreateRole;
import com.bootdo.modules.kekcloak.RoleDO;

@Controller
@RequestMapping("/addRoleForKeyCloak")
public class RoleService {
	
	@RequestMapping("/addRole")
    @ResponseBody
	public static String createRole(@RequestBody RequestCreateRole request)throws Exception
	 {
		//验证token
		System.out.println("申请create role");
		if(!TokenUtil.checkUserToken(request.getUser())){
			return "无效用户请求";
		}
		
		//启动flowable流程
		startFlowable();
		
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", // keycloak地址
				"master", // 指定 Realm master
				"admin", // 管理员账
				"123456", // 管理员密
				"admin-cli");// 指定client
		// 进入 Mytest
		RealmResource realmResource = keycloak.realm(request.getUser().getRealmName());
		RealmRepresentation realm = keycloak.realm(request.getUser().getRealmName()).toRepresentation();
		
		//RoleService.createRole(realmResource, "javaTest2");
		//UserService.createUser(realmResource);
		
		boolean bl = false;
		// 新建 Realm 角色
		RoleRepresentation role = new RoleRepresentation();
		role.setName(request.getCreate_role().getRoleName());
		realmResource.roles().create(role);
		return "角色创建成功";
	 }
	
	public static void startFlowable()
	 {
		String processDefinitionKey = "create_role";
		 
		 ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
	                .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
	                .setJdbcUsername("root")
	                .setJdbcPassword("123")
	                .setJdbcDriver("com.mysql.jdbc.Driver")
	                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

	     ProcessEngine processEngine = cfg.buildProcessEngine();
	     
	        ProcessInstance pi = processEngine.getRuntimeService()
	                .startProcessInstanceByKey(processDefinitionKey);
	        
	        RuntimeService runtimeService = processEngine.getRuntimeService();

	        String processDefinitionId = pi.getProcessDefinitionId();
	        
	        Map<String, Object> variables = new HashMap<String, Object>();
			 variables.put("employee", "jack");
			 variables.put("nrOfHolidays", 3);
			 variables.put("description", "变量");
			 
	        ProcessInstance processInstance =
	                runtimeService.startProcessInstanceById(processDefinitionId,variables);
	        //findTask();
	 }

}
