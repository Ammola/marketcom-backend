package com.ga.marketcom.test;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class API {
	
//	@Autowired
//	private Environment env;
//	
//	@GetMapping("/hello")
//	public String hello() {
//		return "Our app is working fine";
//	}
//	
//	@GetMapping("/")
//	public HashMap<String,String> rootRout(@RequestParam(defaultValue="world") String name){
//		
//		HashMap<String,String> response = new HashMap<String,String>();
//		response.put("message", "Hello"+name+"!");
//		
//		System.out.println(env.getProperty("app.name"));		
//		return response;
//		
//	}

}
