package com.bootdo.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckResult {
	private boolean success;
	private Claims claims;
	private String errCode;
}
