package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.security.service.CustomUserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf()
	    	.disable()
	    	.sessionManagement()
	    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
	    	.and()
	    	.authorizeRequests()
	    	.antMatchers("/oauth/token")
	    	.permitAll()
	    	.anyRequest()
	    	.authenticated();
	  }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	 @Override
	 @Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	 }
	 
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setPasswordEncoder( bCryptPasswordEncoder() );
	    provider.setUserDetailsService(userDetailsService);
	    return provider;
	  }
	 
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	 }
	
}


/*
 * First, we need to enable Spring Security to add the security feature in the application. To configure and enable Spring Security, the @EnableWebSecurity annotation is used
 * 
 * By using @EnableGlobalMethodSecurity, we can easily secure our methods with Java configurations. 
 * Global method security will activate  @PreFilter, @PostFilter , @PreAuthorize, and the @PostAuthorize  annotations 
 * if we want to use them.
 * 
 * Here, WebSecurityConfigurerAdapter  is used to customize security implementation.
 * 
 * 
 * Endpoint /OAuth/token is used to request a token (access or refresh).
 * 
 * We inject a custom implementation of CustomUserDetailsService in order to retrieve user details from the database.
 * 
 * We use the defined BCryptPasswordEncoder bean for password encoding.
 * */
 