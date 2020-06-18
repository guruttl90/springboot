package com.spring.springauthentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.springauthentication.filter.JwtRequestFilter;

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
	
	//Not applied when user create api based authentication
	//Now Provide authority based on the role 
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")			
			.antMatchers("/user").hasAnyRole("USER","ADMIN")			
			.antMatchers("/").permitAll()
			.and().formLogin();			
	}*/
	
	
	//instead of using inmemory authentication going to use jdbc authentication
	//next , we  need to inform our spring security to lookup the user available in the database or not,
	//so we need to configure the datasource 
	
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		//Creating schema and setup data in resources so we dont need default schema and data
		/*.withDefaultSchema()
		.withUser(
				User.withUsername("USER")
					.password("123")
					.roles("USER")
				)
		.withUser(User.withUsername("ADMIN")
				.password("123")
				.roles("ADMIN","USER")
				);
		
	}*/ 
	


/*@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication()
	.dataSource(dataSource)	
	//inform the datasource about the username,password and authority if using our own table
	.usersByUsernameQuery("select username,password,enabled from users where username = ?")
	.authoritiesByUsernameQuery("select username,authority from users where username = ?")
	;
	
}*/
	
	
	//Here we autowire the userDetailsService interface so we need instance to load the instance
	//so we create a class Name MyUserDetailsService it implments UserDetailsService and that class act as a service class so it automatically loaded
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	//To connect mysql database and retrieve the user information for authentication
	//no need of H2 embedded database, we can use our own database and password
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.anyRequest()
			.authenticated()
			//Inform Spring security to dont create Session and manage them for this purpose we using below piece of code
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//jwtRequestFilter filter work before UsernamePasswordAuthenticationFilter
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
		
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {		
		return super.authenticationManagerBean();
	}


	@Bean  //We are not encoding the password 
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
		
}
