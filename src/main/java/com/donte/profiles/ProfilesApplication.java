package com.donte.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)//Necessária pra uso do @PreAuthorize
public class ProfilesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProfilesApplication.class, args);
	}
	
	/*
	@Bean
	CommandLineRunner runner(UserRepository userRepository){
		return args -> {
			userRepository.findAll().forEach(System.out::println);
			System.out.println("------------------------------------------");
			userRepository.findAll().forEach(user->System.out.println(user));
			System.out.println("------------------------------------------");
			for(User user: userRepository.findAll()) {
				System.out.println(user);
			}
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
		};
	}*/	

}
