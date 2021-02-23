package com.ga.marketcom.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.marketcom.model.User;

public interface UserDao extends CrudRepository<User,Integer> {
	
	public User findByEmailAddress(String emailAddress);
	public User findById(int id);
	public User findByResetPassword(String token);
	public User findByShop(int Shop_Id);

}
