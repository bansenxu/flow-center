package com.bootdo.kekcloak;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.rotation.AdapterTokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.adapters.config.AdapterConfig;

import com.alibaba.fastjson.JSON;
import com.bootdo.modules.kekcloak.KeyUserDO;
import com.bootdo.security.TokenDTO;

public class TokenUtil {
	
	
	public static void main(String[] ss)
	{
		KeyUserDO user = new KeyUserDO();
		user.setClientId("admin-cli");
		user.setRealmName("workflow");
		user.setUserName("wangxing");
		user.setPassword("123456");
		System.out.println(getToken(user).getAccess_token());
		//refToken(getToken(user));
	}
	
	/*
	 * curl -X POST -H "Content-Type: application/x-www-form-urlencoded" 
	 * -d 'grant_type=refresh_token&
	 * 	client_id=ROOMIS&
	 * refresh_token={refreshToken}' 
	 * "http://keycloak域名IP/auth/realms/{realms名字}/protocol/openid-connect/token" | jq
	 * */
	public static TokenDTO refToken(TokenDTO old_token)
	{
		String result = "";
		 try {
			 String postURL = "http://127.0.0.1:8180/auth/realms/mytest/protocol/openid-connect/token";
			 PostMethod postMethod = null;
			 postMethod = new PostMethod(postURL) ;
			 postMethod.setRequestHeader("Accept","application/json") ;
			 postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded") ;
			 //参数设置，需要注意的就是里边不能传NULL，要传空字符串
			 NameValuePair[] data = {
			     new NameValuePair("client_id","admin-cli"),
			     new NameValuePair("grant_type","refresh_token"),
			     new NameValuePair("refresh_token",old_token.getRefresh_token())};
			     postMethod.setRequestBody(data);

			     org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			     int response = httpClient.executeMethod(postMethod); // 执行POST方法
			     result = postMethod.getResponseBodyAsString() ;
			     System.out.println("refresh:"+result);
			     TokenDTO td = JSON.parseObject(result,TokenDTO.class);
			     return td;
			 } catch (Exception e) {
			     throw new RuntimeException(e.getMessage());
			 }
	}
	
	public static TokenDTO getToken(KeyUserDO user){
		 //http://127.0.0.1:8180/auth/realms/mytest/protocol/openid-connect/token
		 /*
		  * NameValuePair[] data = {
		  *
			     new NameValuePair("client_id","workflow-app"),
			     new NameValuePair("grant_type","password"),
			     new NameValuePair("username","wangxing"),
			     new NameValuePair("password","123456")};
		  */
		 //http://127.0.0.1:8180/auth/realms/master/protocol/openid-connect/token
		 String result = "";
		 try {
			 String postURL = "http://127.0.0.1:8180/auth/realms/workflow/protocol/openid-connect/token";
			 PostMethod postMethod = null;
			 postMethod = new PostMethod(postURL) ;
			 postMethod.setRequestHeader("Accept","application/json") ;
			 postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded") ;
			 //参数设置，需要注意的就是里边不能传NULL，要传空字符串
			 NameValuePair[] data = {
			     new NameValuePair("client_id",user.getClientId()),
			     new NameValuePair("grant_type","password"),
			     new NameValuePair("username",user.getUserName()),
			     new NameValuePair("password",user.getPassword())};

			     postMethod.setRequestBody(data);

			     org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			     int response = httpClient.executeMethod(postMethod); // 执行POST方法
			     result = postMethod.getResponseBodyAsString() ;
			     System.out.println(result);
			     TokenDTO td = JSON.parseObject(result,TokenDTO.class);
			     return td;
			 } catch (Exception e) {
			     throw new RuntimeException(e.getMessage());
			 }
	 }
	
	public static boolean checkUserToken(KeyUserDO user)
	{
		// 待验证 accessToken
		boolean result = false;
		String tokenString = TokenUtil.getToken(user).getAccess_token();

		// 1、设置client配置信息
		AdapterConfig adapterConfig = new AdapterConfig();
		// realm name
		adapterConfig.setRealm(user.getRealmName());
		// client_id
		adapterConfig.setResource(user.getClientId());
		// 认证中心keycloak地址
		adapterConfig.setAuthServerUrl("http://127.0.0.1:8180/auth");
		// 访问https接口时，禁用证书检查。
		adapterConfig.setDisableTrustManager(true);

		// 2、根据client配置信息构建KeycloakDeployment对象
		KeycloakDeployment deployment = KeycloakDeploymentBuilder.build(adapterConfig);

		// 3、执行token签名验证和有效性检查（不通过会抛异常）
		try {
			AccessToken accesToken = AdapterTokenVerifier.verifyToken(tokenString, deployment);
			System.out.println("验证通过");
			result = true;
		} catch (VerificationException e) {
			e.printStackTrace();
		}
		return result;
	}

}
