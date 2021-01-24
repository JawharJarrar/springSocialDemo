package com.demo.jwt_security.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt_security.domain.User;
import com.demo.jwt_security.models.AuthenticaionRequest;
import com.demo.jwt_security.models.AuthenticationResponse;
import com.demo.jwt_security.repository.UserRepository;
import com.demo.jwt_security.util.JwtUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {
	
	
	@Autowired 
	private  AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@PostMapping("/auth/login")	
	public ResponseEntity<AuthenticationResponse> Login(@RequestBody AuthenticaionRequest  authenticationRequest) throws Exception
	  {
		try {
			 authenticationManager.authenticate
			 		( new UsernamePasswordAuthenticationToken(
			 				
			 				authenticationRequest.getUsername()
			 				,authenticationRequest.getPassword())
			 				
			 		);
			
		}
		catch (BadCredentialsException e) {
			throw new Exception("incorrect username or password", e);
			
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
		 
		
	  }
	
	@PostMapping("/auth/register")	
	public ResponseEntity<User> AddUser (@RequestBody User user) {
		
		User newUser = new User(user.getUsername(),user.getPassword(), user.getEmail(),user.getFirstName(),user.getLastName(),user.getCountry());
		userRepository.save(newUser);
		return ResponseEntity.ok(newUser);	
	}
	
	@DeleteMapping("/delete")	
	public ResponseEntity<?> DeleteUser (@RequestParam long id) {
		
		userRepository.deleteById(id);
		return ResponseEntity.ok(id);	
	}
	
	@GetMapping("/getAll")	
	public ResponseEntity<List<User>> GetAllUsers () {
		
		List<User> Users = userRepository.findAll();
		return ResponseEntity.ok(Users);	
	}
	
}
