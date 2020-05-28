package com.donte.profiles.config.production;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class ConfigProduction {
	
	@PostConstruct
	public void init() {
		System.out.println("Carregando a classe: " + this.getClass().getName());
	}

}
