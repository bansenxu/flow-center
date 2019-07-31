package com.bootdo.system.service;

import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserOnline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

//	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
