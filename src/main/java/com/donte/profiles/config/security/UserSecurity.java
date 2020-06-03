package com.donte.profiles.config.security;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.donte.profiles.model.Userapp;

public class UserSecurity extends User {

	private static final long serialVersionUID = 1L;
	
	private Userapp userApp;
	
	public UserSecurity(Userapp userApp, Set<SimpleGrantedAuthority> authorities) {
		super(userApp.getLogin(), userApp.getPass(), authorities);
		this.userApp = userApp;
	}

	public Userapp getUserApp() {
		return userApp;
	}
	
	@Override
	public boolean isEnabled() {
		return userApp.getActive();
	}
	
}
