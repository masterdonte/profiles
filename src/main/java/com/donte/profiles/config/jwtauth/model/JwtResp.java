package com.donte.profiles.config.jwtauth.model;

public class JwtResp {
	
	private final String token;

	public JwtResp(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

}