package com.spring.springauthentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	

	
	//Instead of storing the username and password in application property , we can configure in this configuration
	//Also need to create bean for PassworEncoder
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("XYZ")
			.password("XYZ")
			.roles("USER")
			.and()
			.withUser("GURU")
			.password("XYZ")
			.roles("ADMIN");
	}*/
	
	
	//Now Provide authority based on the role 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")			
			.antMatchers("/user").hasAnyRole("USER","ADMIN")			
			.antMatchers("/").permitAll()
			.and().formLogin();			
	}
	
	
	//instead of using inmemory authentication going to use jdbc authentication
	//next , we  need to inform our spring security to lookup the user available in the database or not,
	//so we need to configure the datasource 
	
	
	@Autowired
	DataSource dataSource; //We are using H2 embedded database
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(
				User.withUsername("USER")
					.password("123")
					.roles("USER")
				)
		.withUser(User.withUsername("ADMIN")
				.password("123")
				.roles("ADMIN","USER")
				);
		
	}
	
	
	@Bean  //We are not encoding the password 
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}	
	
}
