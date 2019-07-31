package com.bootdo.modules.flowable.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

public class Test2 {
	
	@Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    
    public static void main(String[] args)
    {
//    	Test2 t2 = new Test2();
//    	t2.addExpense("wangxing", 123, "collect");
    	
    	ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
                .setJdbcUsername("root")
                .setJdbcPassword("123")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    	ProcessEngine processEngine = cfg.buildProcessEngine();
    	RuntimeService runtimeService = processEngine.getRuntimeService();

    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("employee", "wangxing");
    	variables.put("nrOfHolidays", 5);
    	variables.put("description", "Have a rest.");
    	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("createRealm", variables);
    	
//    	RepositoryService repositoryService = processEngine.getRepositoryService();
//    	Deployment deployment = repositoryService.createDeployment()
//    	  .addClasspathResource("holiday-request.bpmn20.xml")
//    	  .deploy();
//
//    	ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//    			  .deploymentId(deployment.getId())
//    			  .singleResult();
//    	System.out.println("Found process definition : " + processDefinition.getName());
//    	
    	
    }
    
    public String startFlow(String flowName,String jwt){
    	 //启动流程
    	HashMap<String, Object> map = new HashMap<>();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flowName, map);
        return "提交成功.流程Id为：" + processInstance.getId();
    }

    public String addExpense(String userId, Integer money, String processDefinitionKey) {
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", userId);
        map.put("money", money);
        
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/workflow?useUnicode=true&zeroDateTimeBehavior=convertToNull")
                .setJdbcUsername("root")
                .setJdbcPassword("123")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

     ProcessEngine processEngine = cfg.buildProcessEngine();
        ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey);
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("collect", map);
        System.out.println("提交成功.流程Id为：" + processInstance.getId());
        return "提交成功.流程Id为：" + processInstance.getId();
    }
    
    /**
     * 获取审批管理列表
     */
//    public Object list(String userId) {
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
//        for (Task task : tasks) {
//            System.out.println(task.toString());
//        }
//        return tasks.toArray().toString();
//    }
    
//    public String apply(String taskId) {
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if (task == null) {
//            throw new RuntimeException("流程不存在");
//        }
//        //通过审核
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "通过");
//        taskService.complete(taskId, map);
//        return "processed ok!";
//    }


//    public String reject(String taskId) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "驳回");
//        taskService.complete(taskId, map);
//        return "reject";
//    } 
    
    

}
