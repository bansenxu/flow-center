package com.bootdo.system.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bootdo.modules.flowable.service.impl.FlowForKeycloak;

@Controller
public class FollwController extends AbstractEndpoint{
	
	@GetMapping("/flowable/test")
    String test(Model model) {
        return "/flowable/test";
    }
	
	String flow(String flowType)
	{
		FlowForKeycloak t1 = new FlowForKeycloak();
		t1.init(flowType);
		return "sucess!==="+flowType;
	}

}
