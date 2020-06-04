package com.donte.profiles.config.jwtauth;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

//@Component
public class JwtHandlerException implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 5497458089285363818L;
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
		/*
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
        */
	
		resolver.resolveException(request, response, null, authException);
    }
}