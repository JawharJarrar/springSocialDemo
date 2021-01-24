package com.demo.jwt_security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt_security.domain.Post;
import com.demo.jwt_security.domain.User;
import com.demo.jwt_security.models.AuthenticaionRequest;
import com.demo.jwt_security.models.AuthenticationResponse;
import com.demo.jwt_security.repository.PostRepository;
import com.demo.jwt_security.repository.UserRepository;


@RestController
public class PostController {
	
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	@ResponseBody
	@RequestMapping(value = "/post/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Post> AddPost(@PathVariable("userId")  long userId, @RequestBody Post  post) 
	  {
		User u1  = this.userRepository.getOne(userId);
		Post newPost = new Post(u1, post.getTitle(), post.getContent());
		postRepository.save(newPost);
		return ResponseEntity.ok(post);	
			
		}
	
	@GetMapping("/post/all")	
	public List<Post> getPosts() 
	  {
			List<Post> posts ;
			
        	posts = postRepository.findAll();
        	return posts;
			
		}
	

	  @DeleteMapping("/post/{id}")
	  void deleteEmployee(@PathVariable Long id) {
		  postRepository.deleteById(id);
	  }
}
