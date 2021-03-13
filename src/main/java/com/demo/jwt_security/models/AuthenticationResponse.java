package com.demo.jwt_security.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable { 
	
	private  final String jwt;
	private final UserDto user;
	
	public AuthenticationResponse(String jwt, UserDto user) {
		 this.jwt = jwt;
		 this.user = user;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	public UserDto getUserDto() {
		return user;
	}
	

}
