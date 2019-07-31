package com.bootdo.modules.flowable.resources.api;


import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.common.utils.StringUtils;
import com.bootdo.modules.flowable.service.HiTaskinstService;
import com.bootdo.modules.flowable.service.RuExecutionService;
import com.bootdo.modules.flowable.utils.InputToOutputStreamUtils;
import com.bootdo.modules.flowable.utils.Json2Map;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flowable/taskApi")
public class TaskResources {
    @Autowired
    private RuExecutionService ruExecutionService;
    @Autowired
    private TaskService taskService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HistoryService historyService;

    @Autowired
    private HiTaskinstService hiTaskinstService;
    public final Logger logger = LoggerFactory.getLogger(TaskResources.class);

    /**
     * 2、代办任务列表   offset  开始位置（默认值是0）   limit 查询几条（默认值是10） 必须传递
     *
     * @param params
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/taskList")
    public Object taskList(@RequestParam Map<String, Object> params, String userId) {
        if (StringUtils.isEmpty(userId)) {
            return R.error("待办人的ID不能为空");
        }
        String username = userId;
        logger.info("username:{}", username);
        //默认用户传入a
        params.put("userId", username);
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> ruExecutionList = ruExecutionService.taskList(query);
        int total = ruExecutionService.taskCount(query);
        PageUtils pageUtils = new PageUtils(ruExecutionList, total);
        return pageUtils;
    }

    /**
     * 3、完成任务接口
     *
     * @param taskId
     * @param formJson
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/taskComplete")
    public Object taskComplete(String taskId, String formJson, String userId) {
        boolean completeWithNotForm = false;
        if (StringUtils.isEmpty(userId)) {
            return R.error("待办人的ID不能为空");
        }
        if (StringUtils.isEmpty(taskId)) {
            return R.error("任务Id不能为空");
        }
        if (null == formJson || "".equals(formJson)) {
            completeWithNotForm = true;
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return R.error("任务不存在");
        }
        taskService.claim(taskId, taskId);
        if (completeWithNotForm) {
            taskService.complete(taskId);
        } else {
            Map<String, Object> variables = new HashMap<>();
            try {
                variables = Json2Map.getMap(formJson);
            } catch (Exception e) {
                return R.error("表单数据格式不对");
            }
            try {
                taskService.complete(taskId, variables);
            } catch (Exception e) {
                return R.error(e.getMessage());
            }

        }
        return R.ok();
    }


    /**
     * 4、根据实例查询，实例结束则查询绘制的流程图
     *
     * @param response
     * @param request
     * @param taskId
     * @throws IOException
     */
    @RequestMapping("/showActivityedimageDetailPage")
    public void showActivityedimageDetailPage(HttpServletResponse response, HttpServletRequest request, String taskId) throws IOException {
        if (StringUtils.isEmpty(taskId)) {
            logger.error("任务Id不能为空");
        }
        Task task = taskService.createTaskQuery()
                .taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        String processDefinitionId = "";
        List<String> highLightedActivities = new ArrayList<String>();
        if (task == null) {
            HistoricProcessInstance hp = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            processDefinitionId = hp.getProcessDefinitionId();
        } else {
            processDefinitionId = task.getProcessDefinitionId();
            highLightedActivities.add(task.getTaskDefinitionKey());
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        DefaultProcessDiagramGenerator defaultProcessDiagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream in = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "PNG", highLightedActivities, false);
        OutputStream out = response.getOutputStream();
        InputToOutputStreamUtils.copyPic(in, out);
    }

    /**
     * 5、查询已办任务
     *
     * @param params
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/hisTasklist")
    public Object hisTasklist(@RequestParam Map<String, Object> params, String userId) {
        if (StringUtils.isEmpty(userId)) {
            return R.error("待办人的ID不能为空");
        }
        params.put("userId", userId);
        logger.info("username:{}", userId);
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> hiTaskinstList = hiTaskinstService.list(query);
        int total = hiTaskinstService.count(query);
        PageUtils pageUtils = new PageUtils(hiTaskinstList, total);
        return pageUtils;
    }

    /**
     * 6、查询当前任务相关的变量
     *
     * @param taskId
     * @return
     */
    @ResponseBody
    @GetMapping("/ruVars")
    public Object ruVars(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            return R.error("任务ID不能为空");
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return R.error("任务不存在");
        } else {

        }
        Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
        return variables;
    }
}
