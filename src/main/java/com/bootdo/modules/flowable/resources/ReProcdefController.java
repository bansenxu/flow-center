package com.bootdo.modules.flowable.resources;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.flowable.domain.ActDeCategory;
import com.bootdo.modules.flowable.domain.ReProcdefDO;
import com.bootdo.modules.flowable.service.CategoryService;
import com.bootdo.modules.flowable.service.ReProcdefService;
import com.bootdo.modules.flowable.utils.InputToOutputStreamUtils;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 11:15:19
 */

@Controller
@RequestMapping("/flowable/reProcdef")
public class ReProcdefController extends BaseController{
    @Autowired
    private ReProcdefService reProcdefService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @GetMapping()
//	@RequiresPermissions("system:reProcdef:reProcdef")
    String ReProcdef() {
        return "flowable/reProcdef/reProcdef";
    }

    @ResponseBody
    @GetMapping("/list")
//	@RequiresPermissions("system:reProcdef:reProcdef")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ReProcdefDO> reProcdefList = reProcdefService.list(query);
        for(ReProcdefDO dto : reProcdefList){
            ActDeCategory adc = categoryService.selectByCode(dto.getCategory());
            if(adc!=null){
                dto.setCategoryName(adc.getName());
            }
        }
        int total = reProcdefService.count(query);
        PageUtils pageUtils = new PageUtils(reProcdefList, total);
        return pageUtils;
    }

    @GetMapping("/add")
//	@RequiresPermissions("system:reProcdef:add")
    String add() {
        return "flowable/reProcdef/add";
    }

    @GetMapping("/edit/{id}")
//	@RequiresPermissions("flowable:reProcdef:edit")
    String edit(@PathVariable("id") String id, Model model) {
        ReProcdefDO reProcdef = reProcdefService.get(id);
        model.addAttribute("reProcdef", reProcdef);
        return "flowable/reProcdef/edit";
    }
    @ResponseBody
    @GetMapping("/getVariables")
    public Map<String, Object> getVariables(String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        return  variables;
    }
    /**
     * 启动
     */
    @ResponseBody
    @PostMapping("/formSave")
    public R formSave() {
        Parametermap pm =  this.getParametermap();
        //
        Map<String,Object> vars=new HashMap<>();
        vars.putAll(pm);
        //vars.put("form_key",pm);
        try {
            runtimeService.startProcessInstanceByKey(pm.get("processDefinitionKey").toString(),vars);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
//	@RequiresPermissions("flowable:reProcdef:add")
    public R save(ReProcdefDO reProcdef) {
        if (reProcdefService.save(reProcdef) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 启动实例
     *
     * @param processDefinitionKey
     * @return
     */
    @GetMapping("/startProcessInstanceByKey")
    public String startProcessInstanceByKey(String processDefinitionKey) {
//        return "flowable/form/form_leave";
        String formKey="default";
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).latestVersion()
                .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        Process mainProcess = bpmnModel.getMainProcess();
        List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
        if (startEvents.size()==0){
            //报错误
        }else{
            StartEvent startEvent = startEvents.get(0);
            if (startEvent.getFormKey()!=null)
             formKey = startEvent.getFormKey();
        }
        this.getRequest().setAttribute("formKey",formKey);
        this.getRequest().setAttribute("processDefinitionKey",processDefinitionKey);
        return "flowable/form/form_"+formKey+"";
    }


    /**
     * 完成实例
     *
     * @param taskId
     * @return
     */
    @GetMapping("/formReadonly")
    public String formReadonly(String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        this.getRequest().setAttribute("taskId",taskId);
        for(String k : variables.keySet()){
            this.getRequest().setAttribute(k,variables.get(k));
        }
        return "flowable/form/form_leave_readonly";
    }


//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
//        if (processInstance == null) {
//            return R.error();
//        } else {
//            return R.ok();
//        }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
//	@RequiresPermissions("flowable:reProcdef:edit")
    public R update(ReProcdefDO reProcdef) {
        reProcdefService.update(reProcdef);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
//	@RequiresPermissions("flowable:reProcdef:remove")
    public R remove(String id) {
        if (reProcdefService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
//	@RequiresPermissions("flowable:reProcdef:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        reProcdefService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 显示图片信息
     * @param processId
     * @param request
     * @param response
     * @throws Throwable
     */
    @RequestMapping(value = "/showImage", method = RequestMethod.GET)
    public void showImage(String processId, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processId);

        DefaultProcessDiagramGenerator defaultProcessDiagramGenerator
                = new DefaultProcessDiagramGenerator();

        List<String> highLightedActivities = new ArrayList<>();
        InputStream in = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "PNG", highLightedActivities, false);

        OutputStream out = response.getOutputStream();
        InputToOutputStreamUtils.copyPic(in, out);
    }



}
