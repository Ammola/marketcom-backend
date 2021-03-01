package com.ga.marketcom.dao;
import org.springframework.data.repository.CrudRepository;

import com.ga.marketcom.model.OrderProduct;

public interface OrderProductDao extends CrudRepository<OrderProduct,Integer> {
	
	public OrderProduct findById(int id);
	
}
