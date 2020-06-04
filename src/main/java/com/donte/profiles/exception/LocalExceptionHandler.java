package com.donte.profiles.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LocalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<CustomError> handleBadCredentialsException(BadCredentialsException e) {
		CustomError error = new CustomError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}  
	
	@ExceptionHandler(value = DisabledException.class)
	public ResponseEntity<CustomError> handleDisabledException(DisabledException e) {
		CustomError error = new CustomError(e.getMessage(),HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<CustomError> handleAuthenticationException(AuthenticationException e) {
		CustomError error = new CustomError(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<CustomError> handleAccessDeniedException(AccessDeniedException e) {
		CustomError error = new CustomError(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
}
