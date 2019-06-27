package com.bootdo.system.service;

import org.springframework.stereotype.Service;

@Service
public interface SessionService {
//	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
