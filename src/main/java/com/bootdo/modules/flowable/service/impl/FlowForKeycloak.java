package com.bootdo.modules.flowable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.bootdo.common.config.DruidDBConfig;
import com.bootdo.common.utils.GenUtils;

public class FlowForKeycloak implements JavaDelegate{
	
	 @Override
	 public void execute(DelegateExecution execution) {
	     System.out.println("Calling the external system for employee "
	         + execution.getVariable("employee"));
	 }
	 
//	 public static void main(String[] ss)
//	 {
//		 init("wx_1");
//	 }
	 
	 public static void init(String proName)
	 {
		 String processDefinitionKey = proName;
		 Configuration conf = GenUtils.getKeyCloakConfig();
		 ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
	                .setJdbcUrl(conf.getString("flowable_url"))
	                .setJdbcUsername(conf.getString("flowable_username"))
	                .setJdbcPassword(conf.getString("flowable_password"))
	                .setJdbcDriver(conf.getString("flowable_driver_name"))
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
	 
	 public static void init2()
	 {
		 ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
	                .setJdbcUrl("jdbc:mysql://mysql.it663.com:37101/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
	                .setJdbcUsername("flowable")
	                .setJdbcPassword("2C7jOaLV4RQtpl1q8D0IXuFZ9Boi3hJSsBGmeArNzYUM1PKabfWE6")
	                .setJdbcDriver("com.mysql.jdbc.Driver")
	                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

	     ProcessEngine processEngine = cfg.buildProcessEngine();
	    
		 RepositoryService repositoryService = processEngine.getRepositoryService();
		 
		 Deployment deployment = repositoryService.createDeployment()
		   .addClasspathResource("test1.bpmn20.xml")
		   .deploy();
		 
		 ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				  .deploymentId(deployment.getId())
				  .singleResult();
				System.out.println("Found process definition : " + processDefinition.getId());
					
		 RuntimeService runtimeService = processEngine.getRuntimeService();

		 Map<String, Object> variables = new HashMap<String, Object>();
		 variables.put("employee", "jack");
		 variables.put("nrOfHolidays", 3);
		 variables.put("description", "鍥炲鐪嬬湅");
		 ProcessInstance processInstance = runtimeService.startProcessInstanceById("test1:5:10004");
		         //runtimeService.startProcessInstanceByKey("test1", variables);
	 }
	 
	 public static void main(String[] args) {
		Keycloak keycloak = Keycloak.getInstance("http://kc.it663.com:38000/auth", // keycloak地址
				"master", // 指定 Realm master
				"admin", // 管理员账
				"123456", // 管理员密
				"admin-cli");// 指定client
		// 进入 Mytest
		RealmResource realmResource = keycloak.realm("forjava");
		RealmRepresentation realm = keycloak.realm("forjava").toRepresentation();
		
		//RoleService.createRole(realmResource, "javaTest2");
		//UserService.createUser(realmResource);
			
		List<UserRepresentation>  users = realmResource.users().list();
		for(UserRepresentation tem:users){
			System.out.println(tem.getId());
			System.out.println(tem.getUsername());
		}
			
			
//	 

//	 

//	 
//			// 取得指定用户�? roleMappingResource
//			RoleMappingResource roleMappingResource = realmResource
//					.users()
//					.get(getUser.getId())
//					.roles();
//	 
//			// 为用户分配Realm角色
//			List<RoleRepresentation> realmRolesToAdd = new ArrayList<RoleRepresentation>();
//			RoleRepresentation realmRole = realmResource
//					.roles()
//					.get("MytestRole")
//					.toRepresentation();
//			realmRolesToAdd.add(realmRole);
//			roleMappingResource.realmLevel().add(realmRolesToAdd);
	 
			// 为用户分配client角色
//			List<RoleRepresentation> clientRolesToAdd = new ArrayList<RoleRepresentation>();
//			RoleRepresentation clientRole_ = realmResource
//					.clients()
//					.get("testClientId01")
//					.roles()
//					.get("testClientRoleName01")
//					.toRepresentation();
//			clientRolesToAdd.add(clientRole_);
//			roleMappingResource.clientLevel("testClientId01").add(clientRolesToAdd);
			
		}

}
