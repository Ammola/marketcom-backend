package com.ga.marketcom.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.marketcom.config.JwtUtil;
import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.JwtResponse;
import com.ga.marketcom.model.User;


@RestController
public class UserController {
	
	@Autowired
	private UserDao dao;
	
	@PostMapping("/user/registration")
	public HashMap<String,String> registration (@RequestBody User user){
		HashMap<String,String> response=new HashMap<String,String>();
		
		var it = dao.findAll();
		 
		 for(User dbUser : it) {
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {
				 response.put("message", "User already exists");
				 return response;
			 }
		 }
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(newPassword);
		 
		 dao.save(user);
		 response.put("message", "User registered successfully");
		
		 return response;
	}
	@Autowired
	 AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserDetailsService userDetailService;
	@PostMapping("/user/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody User user){
		try {
			authenticationManager.authenticate(
					 new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword())
					 );
			
		}
		catch(BadCredentialsException e) {
			 String res = "Incorrect username or password";
			 return ResponseEntity.ok(res);
		}
		// Conitnue
				UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmailAddress());
				String jwtToken = jwtUtil.generateToken(userDetails);
				System.out.println(jwtToken);
				return ResponseEntity.ok(new JwtResponse(jwtToken));
	}

}
