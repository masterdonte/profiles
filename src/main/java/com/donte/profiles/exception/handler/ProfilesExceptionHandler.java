package com.donte.profiles.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProfilesExceptionHandler {

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<CustomErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
		CustomErrorResponse error = new CustomErrorResponse("BAD_CREDENTIALS_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.BAD_REQUEST.value()));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}  
	
	@ExceptionHandler(value = DisabledException.class)
	public ResponseEntity<CustomErrorResponse> handleDisabledException(DisabledException e) {
		CustomErrorResponse error = new CustomErrorResponse("DISABLED_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.UNAUTHORIZED.value()));
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}


}
