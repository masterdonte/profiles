package com.donte.profiles.config.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.donte.profiles.model.UserEntity;

public class UserSecurity implements UserDetails {//or extends User

	private static final long serialVersionUID = 1L;
	
	private UserEntity user;
	private Set<SimpleGrantedAuthority> authorities;
	
	public UserSecurity(UserEntity user, Set<SimpleGrantedAuthority> authorities) {
		// super(user.getLogin(), user.getPass(), authorities); // case extends from User
		this.user = user;
		this.authorities = authorities;
	}

	public UserEntity getUser() {
		return user;
	}
	
	@Override
	public boolean isEnabled() {
		return user.getActive();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPass();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
