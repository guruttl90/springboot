package com.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	private static final String RESOURCE_ID = "resource-server-rest-api";
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		 resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
	}

}


/*
 * Next, we need to configure the resource server. 
 * The @EnableResourceServer  annotation, applied on OAuth2 Resource Servers, enables a Spring Security filter that authenticates requests using an incoming OAuth2 token.

	The class ResourceServerConfigurerAdapter implements the ResourceServerConfigure,  providing methods to adjust the access rules and paths that are protected by OAuth2 security.
 * */
 