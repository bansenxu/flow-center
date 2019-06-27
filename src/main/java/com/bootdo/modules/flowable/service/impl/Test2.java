package com.bootdo.modules.flowable.service.impl;

import java.util.HashMap;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;

import com.alibaba.fastjson.JSON;
import com.bootdo.kekcloak.TokenUtil;
import com.bootdo.modules.kekcloak.KeyUserDO;
import com.bootdo.modules.kekcloak.RoleDO;
import com.bootdo.security.TokenDTO;

public class Test2 {
	
//	@Autowired
//    private RuntimeService runtimeService;
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private RepositoryService repositoryService;
//    @Autowired
//    private ProcessEngine processEngine;
    
    public static void main(String[] args)
    {
//    	Test2 t2 = new Test2();
//    	t2.addExpense("wangxing", 123, "collect");
    	
    	KeyUserDO user = new KeyUserDO();
    	user.setClientId("admin-cli");
    	user.setUserName("admin");
    	user.setPassword("123456");
    	user.setRealmName("master");
    	TokenDTO tem = TokenUtil.getToken(user);
    	user.setToken(tem);
    	
    	RoleDO create_role = new RoleDO();
    	create_role.setRoleName("lalala");
    	
    	System.out.println(JSON.toJSON(user));
    	System.out.println(JSON.toJSON(create_role));
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
