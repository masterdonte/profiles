package com.donte.profiles.config.jwtauth;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.donte.profiles.exception.CustomError;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Profile("jwt-auth")
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = tokenService.getUsernameFromToken(jwtToken);
			} catch (MalformedJwtException e) {
				sendErrorFilter(response, "Unable to get JWT Token");
				return;
			} catch (ExpiredJwtException e) {
				sendErrorFilter(response, "JWT Token has expired");
				return;
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (tokenService.validateToken(username, jwtToken)) {
				Collection<GrantedAuthority> auths = tokenService.getAuthoritiesFromToken(jwtToken);
				UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(username, null, auths);
				userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}
		}
		chain.doFilter(request, response);
	}
	
	private void sendErrorFilter(HttpServletResponse response, String message) throws IOException {
		CustomError error = new CustomError(message, HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println(error.toJson());
	}

}