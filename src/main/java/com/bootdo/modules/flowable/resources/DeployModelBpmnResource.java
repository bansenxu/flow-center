package com.bootdo.modules.flowable.resources;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.rest.app.AbstractModelBpmnResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/deploy")
public class DeployModelBpmnResource extends AbstractModelBpmnResource {

    @Autowired
    private RepositoryService repositoryService;


    /**
     * http://127.0.0.1:8001/delpoy/rest/models/3eabbfe0-c390-11e8-a370-0242ac110005/bpmn20
     * GET /rest/models/{modelId}/bpmn20 -> Get BPMN 2.0 xml
     */
    @RequestMapping(value = "/rest/models/{processModelId}/bpmn20", method = RequestMethod.GET)
    public void getProcessModelBpmn20Xml(HttpServletResponse response, @PathVariable String processModelId) throws IOException {
        //super.getProcessModelBpmn20Xml(response, processModelId);
        getProcessModelBpmn20XmlToModel(response,processModelId);
    }

    public void getProcessModelBpmn20XmlToModel(HttpServletResponse response, String processModelId) throws IOException {

        if (processModelId == null) {
            throw new BadRequestException("No process model id provided");
        }

        Model model = modelService.getModel(processModelId);

        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        byte[] xmlBytes = modelService.getBpmnXML(bpmnModel);

        Deployment deployment= repositoryService.createDeployment().addBytes("shareniu.bpmn20.xml",xmlBytes).deploy();
    }
}
