package com.bootdo.kekcloak.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.GenUtils;

@Controller
@RequestMapping("/startFlow")
public class FlowService {
	
	@RequestMapping("/addRole")
    @ResponseBody
	public void addRoleFlowStart()
	{
		String processDefinitionKey = "create_role";
		Configuration conf = GenUtils.getKeyCloakConfig();
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl(conf.getString("flowable_url")).setJdbcUsername(conf.getString("flowable_username"))
				.setJdbcPassword(conf.getString("flowable_password"))
				.setJdbcDriver(conf.getString("flowable_driver_name"))
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		ProcessEngine processEngine = cfg.buildProcessEngine();

		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);

		RuntimeService runtimeService = processEngine.getRuntimeService();

		String processDefinitionId = pi.getProcessDefinitionId();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("realmName", "123");
		variables.put("roleName", "456");

		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		// findTask();
	}

}
