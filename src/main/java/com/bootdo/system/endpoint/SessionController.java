package com.bootdo.system.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/online")
public class SessionController {

	@GetMapping()
	public String online() {
		return "system/online/online";
	}

//	@ResponseBody
//	@RequestMapping("/list")
//	public List<UserOnline> list() {
//		return sessionService.list();
//	}

//	@ResponseBody
//	@RequestMapping("/forceLogout/{sessionId}")
//	public R forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
//		try {
//			sessionService.forceLogout(sessionId);
//			return R.ok();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return R.error();
//		}
//
//	}

//	@ResponseBody
//	@RequestMapping("/sessionList")
//	public Collection<Session> sessionList() {
//		return sessionService.sessionList();
//	}


}
