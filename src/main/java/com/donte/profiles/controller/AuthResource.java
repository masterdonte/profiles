package com.donte.profiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donte.profiles.config.jwtauth.JwtTokenService;
import com.donte.profiles.config.jwtauth.model.JwtLogin;
import com.donte.profiles.config.jwtauth.model.JwtResp;

@Profile("jwt-auth")
@RestController
@RequestMapping("/token")
public class AuthResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtLogin req) throws Exception {
		UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
		Authentication authenticate = authenticationManager.authenticate(userPassAuthToken);
		final String token = tokenService.generateToken(authenticate);
		return ResponseEntity.ok(new JwtResp(token));
	}
	
}