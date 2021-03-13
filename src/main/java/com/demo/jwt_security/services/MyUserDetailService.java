package com.demo.jwt_security.services;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.jwt_security.repository.UserRepository;

@Service
public class MyUserDetailService  implements UserDetailsService, Serializable{

	@Autowired
	UserRepository  userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String password = userRepository.getPasswordByUsername(username);

		return new  User(username,password ,new ArrayList<>());
	}

}
