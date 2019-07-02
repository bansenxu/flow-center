package com.bootdo.kekcloak.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.GenUtils;
import com.bootdo.kekcloak.TokenUtil;
import com.bootdo.modules.kekcloak.KeyUserDO;
import com.bootdo.modules.kekcloak.RequestCreateRole;

@Controller
@RequestMapping("/addUserForKeyCloak")
public class UserService {
	
	 public static void getUsers(KeyUserDO user)
	 {
		 try {
			 String postURL = "http://127.0.0.1:8180/auth/realms/master/users";
			 PostMethod postMethod = null;
			 postMethod = new PostMethod(postURL) ;
			 postMethod.setRequestHeader("Accept","application/json") ;
			 postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded") ;
			 postMethod.setRequestHeader("Authorization", "bearer "+user.getToken().getAccess_token());
			 //参数设置，需要注意的就是里边不能传NULL，要传空字符串
			 NameValuePair[] data = {
			     new NameValuePair("username","wangxing")};

			 postMethod.setRequestBody(data);

			     org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			     int response = httpClient.executeMethod(postMethod); // 执行POST方法
			     String result = postMethod.getResponseBodyAsString() ;

			     System.out.println(result);
			 } catch (Exception e) {
			     throw new RuntimeException(e.getMessage());
			 }
	 }
	
	@RequestMapping("/addUser")
	@ResponseBody
	public static String createUser(@RequestBody RequestCreateRole request)
	 {
		//验证token
		System.out.println("申请create role");
		if (!TokenUtil.checkUserToken(request.getUser())) {
			return "无效用户请求";
		}
		
		Configuration conf = GenUtils.getKeyCloakConfig();
		Keycloak keycloak = Keycloak.getInstance(conf.getString("url"), // keycloak地址
				conf.getString("realmname"), // 指定 Realm master
				conf.getString("username"), // 管理员账
				conf.getString("passworld"), // 管理员密
				conf.getString("client_id"));// 指定client
		// 进入 Mytest
		RealmResource realmResource = keycloak.realm(request.getUser().getRealmName());
		//RealmRepresentation realm = keycloak.realm(create_user.getRealmName()).toRepresentation();
		// 新建用户
		UserRepresentation user = new UserRepresentation();
		// 设置登录账号
		user.setUsername(request.getUser().getUserName());
		// 设置账号“启用�??
		user.setEnabled(true);
		// 设置密码
		List<CredentialRepresentation> credentials = new ArrayList<CredentialRepresentation>();
		CredentialRepresentation cr = new CredentialRepresentation();
		cr.setType(CredentialRepresentation.PASSWORD);
		cr.setValue(request.getUser().getPassword());
		cr.setTemporary(false);
		credentials.add(cr);
		user.setCredentials(credentials);

		// 设置自定义用户属�?
		// Map<String, List<String>> attributes = new HashMap<String,
		// List<String>>();
		// List<String> list = new ArrayList<String>();
		// list.add("音乐");
		// list.add("美术");
		// attributes.put("爱好", list);
		// user.setAttributes(attributes);

		// 创建用户
		realmResource.users().create(user);
		return "创建用户成功";
		// 根据 username 查找用户
		//UserRepresentation getUser = realmResource.users().search("zhangsan").get(0);
	 }

}
