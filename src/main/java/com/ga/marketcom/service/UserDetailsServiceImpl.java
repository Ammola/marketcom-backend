package com.ga.marketcom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.User;
import com.ga.marketcom.model.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired 
	UserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException{
		User user=dao.findByEmailAddress(emailAddress);
		UserDetailsImpl obj= new UserDetailsImpl(user);
		return obj;
	}

}
