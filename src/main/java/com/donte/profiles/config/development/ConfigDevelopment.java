package com.donte.profiles.config.development;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class ConfigDevelopment {

	@PostConstruct
	public void init() {
		System.out.println("Carregando a classe: " + this.getClass().getName());
	}
	
}
