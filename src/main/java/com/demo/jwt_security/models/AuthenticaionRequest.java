package com.demo.jwt_security.models;

import java.io.Serializable;

public class AuthenticaionRequest implements Serializable { 
	
	private String username;
	private String password;
	
	public AuthenticaionRequest(String username, String password) {
	    this.setUsername(username);
        this.setPassword(password);
	}
	
    public AuthenticaionRequest()
    {

    }
    
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
