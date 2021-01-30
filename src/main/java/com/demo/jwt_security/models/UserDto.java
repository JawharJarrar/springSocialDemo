package com.demo.jwt_security.models;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.demo.jwt_security.domain.Comment;
import com.demo.jwt_security.domain.Post;

public class UserDto {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	private	String username;
	private	String firstName;

	private	String lastName;
	
	private	Date birthdate;
	
	private	String country;
	


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		birthdate = birthdate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		country = country;
	}
	private String password;
	private String email;
	
	
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	public void User(String username,String email,String firstName,String lastName,String country) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.email = email;
	}
	public void  User() {
	
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
