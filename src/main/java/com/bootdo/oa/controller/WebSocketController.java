package com.bootdo.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.bootdo.system.domain.UserDO;

@Controller
public class WebSocketController {
	@Autowired
	SimpMessagingTemplate template;
	
	//广播推送消息
    @Scheduled(fixedRate = 10000)
    public void sendTopicMessage() {
		System.out.println("后台广播推送！");
		UserDO user=new UserDO();
		user.setName("oyzc");
    	this.template.convertAndSend("/topic/getResponse",user);
    }
    
    //一对一推送消息
    @Scheduled(fixedRate = 10000)
    public void sendQueueMessage() {
		System.out.println("后台一对一推送！");
		UserDO user=new UserDO();
		user.setUserId(1L);
		user.setName("oyzc");
		this.template.convertAndSendToUser(user.getUserId()+"","/queue/getResponse",user);
    }

  //单独聊天
    @MessageMapping("/aloneRequest")	
    public UserDO alone(UserDO chatRoomRequest){
        //方法用于一对一测试
    	System.out.println("userId = " + chatRoomRequest.getUserId());
        System.out.println("name = " + chatRoomRequest.getName());       
        UserDO response=new UserDO();
        response.setName(chatRoomRequest.getName());       
        response.setUserId(chatRoomRequest.getUserId());
        this.template.convertAndSendToUser(chatRoomRequest.getUserId()+"","/alone/getResponse",response);
        return response;
    }
    
	/*@Autowired
	WelcomeTask welcomeTask;

	@MessageMapping("/welcome") // 浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
	@SendTo("/topic/getResponse") // 服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
	public Response say(Message message) throws Exception {
		Thread.sleep(1000);
		return new Response("Welcome, " + message.getName() + "!");
	}

	@GetMapping("/test")
	String test() {
		return "test";
	}

	@RequestMapping("/welcome")
	@ResponseBody
	public R say02() {
		try {
			welcomeTask.sayWelcome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.ok();
	}*/
//    @ResponseBody
//    @GetMapping("/chat")
//    public String  handleChat(Principal principal, String msg) {
//        template.convertAndSendToUser(sessionService.listPrincipal().get(0).toString(), "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
//        return sessionService.listPrincipal().get(0).toString();
//    }
}