package com.demo.jwt_security.config;

import io.jsonwebtoken.Claims;

import javax.servlet.Filter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demo.jwt_security.filters.JwtRequestFilter;
import com.demo.jwt_security.services.MyUserDetailService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	 private MyUserDetailService myUserDetailService;
	
	@Autowired
	 private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		  http.csrf().disable().authorizeRequests().antMatchers("/auth/**").permitAll()

																	.anyRequest()
																		.authenticated()
																		.and().sessionManagement()
																	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
																			
	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);	
	  http.cors().and().authorizeRequests();																		
												
	}
	 
	@Bean
	public  PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}



	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	


	
}
