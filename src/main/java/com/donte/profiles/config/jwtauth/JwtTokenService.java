package com.donte.profiles.config.jwtauth;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.donte.profiles.utils.SystemProps;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Profile("jwt-auth")
@Component
public class JwtTokenService{
	
	private final static String ROLES = "ROLES";

	@Autowired
	private SystemProps props;

	public Collection<GrantedAuthority> getAuthoritiesFromToken(String token) {
		Collection<GrantedAuthority> authorities = getRolesFromToken(token)
				.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return authorities;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public String generateToken(Authentication auth) {
		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		
		return Jwts.builder()
				.setSubject(auth.getName())
				.claim(ROLES, roles)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + props.getJwtExpiration() * 1000))
				.signWith(SignatureAlgorithm.HS512, props.getJwtSecret())
				.compact();
	}

	public Boolean validateToken(String username, String token) {
		final Claims claims = getAllClaimsFromToken(token);
		final String subject = claims.getSubject();
		final Date expiration = claims.getExpiration();
		return (username.equals(subject) && expiration.after(new Date()));
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getRolesFromToken(String token) {
		//final Claims claims = getAllClaimsFromToken(token);
		//List<String> roles = claims.get(ROLES, List.class);
		return getClaimFromToken(token, claims -> (List<String>) claims.get(ROLES));
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(props.getJwtSecret()).parseClaimsJws(token).getBody();
	}

}