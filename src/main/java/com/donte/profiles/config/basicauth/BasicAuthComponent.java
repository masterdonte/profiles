package com.donte.profiles.config.basicauth;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("basic-auth")
@Component
public class BasicAuthComponent {
	
	@PostConstruct
	public void init() {
		System.out.println("Carregando a classe: " + this.getClass().getName());
	}

}
