package com.bootdo.modules.flowable.resources.api;

import com.bootdo.common.utils.R;
import com.bootdo.modules.flowable.utils.Json2Map;
import com.bootdo.modules.flowable.utils.UUIDUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/flowable/processDefinition")
public class ProcessDefinitionResources {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    public final Logger logger = LoggerFactory.getLogger(ProcessDefinitionResources.class);

    /**1、启动实例
     * @param processDefinitionKey
     * @param businessKey
     * @param formJson             格式{"a":"a","b":"b"}
     */
    @GetMapping("/start")
    public R startProcessInstanceByKey(String processDefinitionKey, String businessKey, String formJson) {
        boolean startWithNotForm = false;
        if (null == processDefinitionKey || "".equals(processDefinitionKey)) {
            logger.error("流程key没有");
            return R.error("流程key没有");
        }
        if (null == businessKey || "".equals(businessKey)) {
            businessKey = UUIDUtils.uuid();
        }
        if (null == formJson || "".equals(formJson)) {
            startWithNotForm = true;
        }
        if (startWithNotForm) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        } else {
            Map<String, Object> variables = new HashMap<>();
            try {
                variables = Json2Map.getMap(formJson);
            } catch (Exception e) {
                return R.error("表单数据格式不对");
            }
            try {
                runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
            } catch (Exception e) {
                return R.error(e.getMessage());
            }

        }
        return R.ok();

    }



}
