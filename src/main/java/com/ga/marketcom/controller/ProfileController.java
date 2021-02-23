package com.ga.marketcom.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ga.marketcom.config.JwtUtil;
import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.User;

import io.jsonwebtoken.ExpiredJwtException;



@RestController
public class ProfileController {
	
	@Autowired 
	private UserDao dao;
	@Autowired
    private JwtUtil jwtUtil;
	
	@GetMapping("/user/profile")
	public User sowProfileIndex (HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }}
		User user=new User();
		user=dao.findByEmailAddress(username);
		System.out.println(user.getFirstName());
		dao.save(user);
		return user;
		
	}
	@GetMapping("/user/editPersonalInfo")
	public User getAuthor(@RequestBody User user) {
		user=dao.findByEmailAddress(user.getEmailAddress());
		 
		return user;
	}
//	public User editAuthor(@RequestParam int id) {
//		//User user = dao.findById(id);
//		 User user = dao.findById(id);
//		return user;
//	}
	@PutMapping("/user/editPersonalInfo")
	public User updateUser(@RequestBody User user) {
		BCryptPasswordEncoder token = new BCryptPasswordEncoder();
		String emailAddress=user.getEmailAddress();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		String currentPassword=user.getPassword();
		String newPassword=user.getResetPassword();
		user=dao.findByEmailAddress(emailAddress);
		if(null != currentPassword)
	        if(token.matches(currentPassword, user.getPassword())) {
	            if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
	            	user.setPassword(token.encode(newPassword));
	            }
	            
	        } 
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setResetPassword(null);
		dao.save(user); 
		return user;
	}

}
