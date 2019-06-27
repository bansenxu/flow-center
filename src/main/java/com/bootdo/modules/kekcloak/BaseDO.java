package com.bootdo.modules.kekcloak;

import com.bootdo.security.TokenDTO;

public class BaseDO {
	
	private String realmName;
	
	private String clientId;
	
	TokenDTO token;

	public String getRealmName() {
		return realmName;
	}

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

	public TokenDTO getToken() {
		return token;
	}

	public void setToken(TokenDTO token) {
		this.token = token;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
