package com.ga.marketcom.dao;

import org.springframework.data.repository.CrudRepository;
import com.ga.marketcom.model.Cart;

public interface CartDao extends CrudRepository<Cart,Integer> {
	public Cart findById(int id);
}
