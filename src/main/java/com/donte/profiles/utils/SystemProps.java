package com.donte.profiles.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("system")
public class SystemProps {
	
	private String jwtSecret;
	private long jwtExpiration;

	public String getJwtSecret() {
		return jwtSecret;
	}
	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
	public long getJwtExpiration() {
		return jwtExpiration;
	}
	public void setJwtExpiration(long jwtExpiration) {
		this.jwtExpiration = jwtExpiration;
	}
	@Override
	public String toString() {
		return "SystemProps [jwtSecret=" + jwtSecret + ", jwtExpiration=" + jwtExpiration + "]";
	}
	
}
