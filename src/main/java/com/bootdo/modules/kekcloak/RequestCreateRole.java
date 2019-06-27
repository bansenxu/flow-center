package com.bootdo.modules.kekcloak;

public class RequestCreateRole {

	private KeyUserDO user;
	
	private RoleDO create_role;

	public KeyUserDO getUser() {
		return user;
	}

	public void setUser(KeyUserDO user) {
		this.user = user;
	}

	public RoleDO getCreate_role() {
		return create_role;
	}

	public void setCreate_role(RoleDO create_role) {
		this.create_role = create_role;
	}
}
