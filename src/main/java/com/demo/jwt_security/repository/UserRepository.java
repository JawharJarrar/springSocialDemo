package com.demo.jwt_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.jwt_security.domain.User;


public interface UserRepository  extends JpaRepository<User, Long> {
	 
    @Query("SELECT u.password FROM User u where u.username = (:name)")
    public String  getPasswordByUsername(String name ); 
    public User findByUsername(String username);

	}


