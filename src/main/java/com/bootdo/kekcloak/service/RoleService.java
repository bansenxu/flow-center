package com.bootdo.kekcloak.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.kekcloak.TokenUtil;
import com.bootdo.modules.kekcloak.KeyUserDO;
import com.bootdo.modules.kekcloak.RoleDO;

@Controller
@RequestMapping("/addRoleForKeyCloak")
public class RoleService {
	
	@RequestMapping("/addRole")
    @ResponseBody
	public static String createRole(KeyUserDO user ,RoleDO create_role)throws Exception
	 {
		System.out.println("申请create role");
		if(!TokenUtil.checkUserToken(user)){
			return "无效用户请求";
		}
		Keycloak keycloak = Keycloak.getInstance("http://localhost:8180/auth", // keycloak地址
				"master", // 指定 Realm master
				"admin", // 管理员账
				"123456", // 管理员密
				"admin-cli");// 指定client
		// 进入 Mytest
		RealmResource realmResource = keycloak.realm(create_role.getRealmName());
		//RealmRepresentation realm = keycloak.realm(create_role.getRealmName()).toRepresentation();
		
		//RoleService.createRole(realmResource, "javaTest2");
		//UserService.createUser(realmResource);
		
		boolean bl = false;
		// 新建 Realm 角色
		RoleRepresentation role = new RoleRepresentation();
		role.setName(create_role.getRoleName());
		realmResource.roles().create(role);
		return "角色创建成功";
	 }

}
