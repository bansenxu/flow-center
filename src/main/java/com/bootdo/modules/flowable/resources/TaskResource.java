package com.bootdo.modules.flowable.resources;

import com.bootdo.common.utils.R;
import com.bootdo.modules.flowable.utils.InputToOutputStreamUtils;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flowable/task")
public class TaskResource {

    @Autowired
    private TaskService taskService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HistoryService historyService;

    /**
     * 完成任务
     */
    @ResponseBody
    @PostMapping("/complete")
    public R save(String taskId) {
        try {
            //TODO 首先要进行任务的签收，然后完成
            taskService.claim(taskId,"a");
            taskService.complete(taskId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据实例查询，实例结束则查询绘制的流程图
     *
     * @param response
     * @param request
     * @param taskId
     * @throws IOException
     */
    @RequestMapping("/showActivityedimageDetailPage")
    public void showActivityedimageDetailPage(HttpServletResponse response, HttpServletRequest request, String taskId) throws IOException {

        Task task = taskService.createTaskQuery()
                .taskId(taskId).singleResult();
        String processInstanceId=task.getProcessInstanceId();
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


        InputStream in = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "PNG", highLightedActivities,false);

        OutputStream out = response.getOutputStream();
        InputToOutputStreamUtils.copyPic(in, out);
    }


    /**
     * 根据实例查询，实例结束则查询绘制的流程图
     * @param response
     * @param request
     * @param processInstanceId
     * @throws IOException
     */
    @RequestMapping("/showActivityedimageDetailPageByProcessInstanceId")
    public void showActivityedimageDetailPageByProcessInstanceId(HttpServletResponse response, HttpServletRequest request,String processInstanceId) throws IOException {


        String processDefinitionId ="";
        List<String> highLightedActivities=new ArrayList<String>();
        List<Task> task = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

        if (task.size()>0) {
            processDefinitionId = task.get(0).getProcessDefinitionId();
            for (Task t: task) {
                highLightedActivities.add(t.getTaskDefinitionKey());
            }
        }else {

            HistoricProcessInstance hp = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            processDefinitionId=hp.getProcessDefinitionId();


        }

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        DefaultProcessDiagramGenerator defaultProcessDiagramGenerator = new 	DefaultProcessDiagramGenerator();


        InputStream in = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "PNG", highLightedActivities,false);

        OutputStream out= response.getOutputStream();
        InputToOutputStreamUtils.copyPic(in,out);
    }

}
