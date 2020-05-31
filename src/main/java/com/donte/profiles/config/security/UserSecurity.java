package com.donte.profiles.config.security;

import org.springframework.security.core.userdetails.User;

import com.donte.profiles.model.UserApp;

public class UserSecurity extends User {

	private static final long serialVersionUID = 1L;
	
	private UserApp userApp;
	
	public UserSecurity(UserApp userApp) {
		super(userApp.getLogin(), userApp.getPass(), userApp.getRoles());
		this.userApp = userApp;
	}

	public UserApp getUserApp() {
		return userApp;
	}
	
}
