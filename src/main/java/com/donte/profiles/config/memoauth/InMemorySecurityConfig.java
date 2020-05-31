package com.donte.profiles.config.memoauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("memo-auth")
@Configuration
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

	//Eh possivel utilizar a config abaixo sem extender WebSecurityConfigurerAdapter
	@Autowired //independente de extender a classe ou nao
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder
		.inMemoryAuthentication()
		.withUser("admin").password("{noop}123").roles("USER", "ADMIN")
		.and()
		.withUser("user").password("{noop}123").roles("USER");
	}
	
	//Nao é possivel acessar o h2-console sem extender WebSecurityConfigurerAdapter
	//Pelo menos nao consegui
	public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").hasAnyRole("ADMIN").
		and().formLogin();
        httpSecurity.csrf().disable();
        //A linha abaixo libera os frames do h2-console
        httpSecurity.headers().frameOptions().disable();
    }
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//            .ignoring()
//            .antMatchers("/h2-console/**");
//    }

}

