package com.bootdo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.rotation.AdapterTokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.adapters.config.AdapterConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.bootdo.kekcloak.TokenUtil;
import com.bootdo.modules.kekcloak.KeyUserDO;

public class JwtUnit {
	
	/*
	 * 过期时间配置
	 * */
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	
	/*
	 * token 私钥
	 * */
	private static final String TOKEN_SECRET = "e4013171-a621-4de7-b05f-0b07b4e6e9dd";

	/*
	 * 生成签名，15min后过期
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
	
	public static void main(String[] ss) {
		
		
	}

	/*
	 * 验签
	 * */
	public static boolean verify(String token){
		try{
			token = sign("wangxing","123456-oiu");
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifyer = JWT.require(algorithm).build();
			DecodedJWT jwt = verifyer.verify(token);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
