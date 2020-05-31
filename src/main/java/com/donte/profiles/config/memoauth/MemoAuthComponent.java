package com.donte.profiles.config.memoauth;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("memo-auth")
@Component
public class MemoAuthComponent {

	@PostConstruct
	public void init() {
		System.out.println("Carregando a classe: " + this.getClass().getName());
	}
	
}
