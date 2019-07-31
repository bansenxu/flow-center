package com.bootdo.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUnit {
	
	/*
	 * 过期时间配置
	 * */
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	
	/*
	 * token 私钥
	 * */
	private static final String TOKEN_SECRET = "fbfc09f1-e786-45de-9675-950657a8213a";
	
	public static void main(String[] ss)throws Exception {
//		KeyUserDO user = new KeyUserDO();
//		user.setClientId("admin-cli");
//		user.setRealmName("master");
//		user.setUserName("wangxing");
//		user.setPassword("123456");
//		String token = TokenUtil.getToken(user).getAccess_token();
//		System.out.println(verify(sign("wangxing","123456-oiu",token)));
    	
//    	String jwt = createJWT("wangxing", "{'a':'wx','b':123}", 1000l);
//    	CheckResult re = validateJWT(jwt);
//    	System.out.println(re.getErrCode());
		
		String jwts = sign("wangxing","123"); //签发
		
		String[] res = jwts.split(".");
		System.out.println(jwts);
		
		String header = jwts.substring(0,jwts.indexOf("."));
		jwts = jwts.substring(jwts.indexOf(".")+1);
		String payload = jwts.substring(0,jwts.indexOf("."));
		String sign = jwts.substring(jwts.indexOf(".")+1);
		
		System.out.println("header=="+header);
		System.out.println("payload=="+payload);
		System.out.println("sign=="+sign);
		
		System.out.println(new String(Base64.getDecoder().decode(header)));
		System.out.println(new String(Base64.getDecoder().decode(payload)));
		//System.out.println(Base64.decode();
//		System.out.println(verify(jwts));//验签
//		
//		Thread.sleep(2500);
//		
//		System.out.println(verify(jwts));//验签
	}

	/*
	 * 生成签名，2秒后过期
	 * jwt 签发
	 * */
	public static String sign(String username,String userId){
		try{
			Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
			Algorithm algorithm  = Algorithm.HMAC256(TOKEN_SECRET);
			Map<String,Object> header = new HashMap<String,Object>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			return JWT.create().withHeader(header)
					.withClaim("loginName", username)
					.withClaim("userId", userId)
					.withExpiresAt(date)
					.sign(algorithm);
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 验签
	 * */
	public static boolean verify(String sign){
		try{
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifyer = JWT.require(algorithm).build();
			DecodedJWT jwt = verifyer.verify(sign);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
