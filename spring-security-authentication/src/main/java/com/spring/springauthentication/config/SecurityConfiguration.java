package com.spring.springauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	//Instead of storing the username and password in application property , we can configure in this configuration
	//Also need to create bean for PassworEncoder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("XYZ")
			.password("XYZ")
			.roles("USER")
			.and()
			.withUser("GURU")
			.password("XYZ")
			.roles("ADMIN");
	}

	
	@Bean
	public PasswordEncoder getEncodedPassword() {
		return NoOpPasswordEncoder.getInstance();
	}


	//Now Provide authority based on the role 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")			
			.antMatchers("/user").hasAnyRole("USER","ADMIN")			
			.antMatchers("/").permitAll()
			.and().formLogin();			
	}
	
	

	
	
}
