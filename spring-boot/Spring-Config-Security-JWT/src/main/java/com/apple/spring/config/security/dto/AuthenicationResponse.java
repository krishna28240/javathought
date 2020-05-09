package com.apple.spring.config.security.dto;

public class AuthenicationResponse {
	private String jwt;
	public AuthenicationResponse(String token) {
		this.jwt=token;
	}
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
