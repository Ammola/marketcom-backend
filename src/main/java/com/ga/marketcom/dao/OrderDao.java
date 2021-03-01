package com.ga.marketcom.dao;
import org.springframework.data.repository.CrudRepository;

import com.ga.marketcom.model.Order;


public interface OrderDao extends CrudRepository<Order,Integer> {
	
	public Order findById(int id);
	
}
