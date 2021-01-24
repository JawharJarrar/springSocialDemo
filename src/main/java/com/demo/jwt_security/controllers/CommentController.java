package com.demo.jwt_security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt_security.domain.Comment;
import com.demo.jwt_security.domain.Post;
import com.demo.jwt_security.domain.User;
import com.demo.jwt_security.repository.CommentRepository;
import com.demo.jwt_security.repository.PostRepository;
import com.demo.jwt_security.repository.UserRepository;

@RestController
public class CommentController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private CommentRepository commentRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	@ResponseBody
	@RequestMapping(value = "/comment/{userId}/{postId}", method = RequestMethod.POST)
	public ResponseEntity<Comment> AddComment(@PathVariable("userId")  long userId,@PathVariable("postId")  long postId, @RequestBody Comment  comment) 
	  {
		User commentUser  = this.userRepository.getOne(userId);
		Post commentPost  = this.postRepository.getOne(postId);
		Comment Postcomment = new Comment(comment.getContent(),commentPost,commentUser);
		commentRepository.save(Postcomment);
		return ResponseEntity.ok(Postcomment);	
			
		}
	
	@GetMapping("/comment/all")	
	public List<Comment> getComments() 
	  {
			List<Comment> comments ;
			
			comments = commentRepository.findAll();
        	return comments;
			
		}
	

	  @DeleteMapping("/comment/{id}")
	  void deleteEmployee(@PathVariable Long id) {
		  commentRepository.deleteById(id);
	  }

}
