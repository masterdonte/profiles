package com.donte.profiles.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donte.profiles.model.UserEntity;
import com.donte.profiles.repository.UserRepository;
import com.donte.profiles.utils.SystemProps;

@RestController
@RequestMapping("/home")
public class HomeResource {
	
	@Autowired
	private SystemProps props;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test")
	@PreAuthorize("hasRole('USER')")
	public String propriedades() {
		return props.toString();
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') and hasAuthority('ROLE_ADMIN')")
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/login")
	@PreAuthorize("hasRole('MANAGER')")
	public String getStatus1(Principal principal) {
		return principal.getName();
	}

	@GetMapping("/roles")
	public String getStatus2(Authentication authentication) {
		String msg = authentication.getName() + ", You have";
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
                    msg += " " + role;
		}
		return msg;
	}
	
	@GetMapping("/nome")
	@PreAuthorize("hasRole('MANAGER')")
	public String getStatus3(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}

}
