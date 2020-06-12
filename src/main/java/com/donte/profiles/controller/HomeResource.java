package com.donte.profiles.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donte.profiles.event.RecursoCriadoEvent;
import com.donte.profiles.model.Categoria;
import com.donte.profiles.model.UserEntity;
import com.donte.profiles.repository.CategoriaRepository;
import com.donte.profiles.repository.UserRepository;
import com.donte.profiles.utils.SystemProps;

@RestController
@RequestMapping("/home")
public class HomeResource {
	
	@Autowired
	private SystemProps props;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/test")
	@PreAuthorize("hasRole('USER')")
	public String propriedades() {
		return props.toString();
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') and hasAuthority('ROLE_ADMIN')")
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/login")
	@PreAuthorize("hasRole('MANAGER')")
	public String getStatus1(Principal principal) {
		return principal.getName();
	}

	@GetMapping("/roles")
	public String getStatus2(Authentication authentication) {
		String msg = authentication.getName() + ", You have";
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
                    msg += " " + role;
		}
		return msg;
	}
	
	@GetMapping("/nome")
	@PreAuthorize("hasRole('MANAGER')")
	public String getStatus3(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}
	
	@GetMapping("/categorias")
	@PreAuthorize("hasRole('USER')")
	public List<Categoria> getCategorias() {
		System.out.println("Chegou");
		return categoriaRepository.findAll();
	}
	
	@PostMapping("/categorias")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Categoria> setCategorias(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria catSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, catSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(catSalva);
	}

}
