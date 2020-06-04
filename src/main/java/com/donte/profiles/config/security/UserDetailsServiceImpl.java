package com.donte.profiles.config.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.donte.profiles.model.UserEntity;
import com.donte.profiles.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByLogin(username);
		if(user == null)
			throw new UsernameNotFoundException("Wrong username or password");
		return new UserSecurity(user, getAuthorities(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
	}
	
	/*
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 // Here we are using dummy data, you need to load user data from database or other third party application
		User user = findUserbyUername(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	},*/

}
