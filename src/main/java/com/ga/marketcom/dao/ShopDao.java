package com.ga.marketcom.dao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ga.marketcom.model.Shop;

public interface ShopDao extends CrudRepository<Shop,Integer> {
	
	public Shop findById(int id);
	
	@Modifying
	@Query(value = "DELETE FROM meal_cart WHERE cart_id =:cart_id", nativeQuery = true)
	void deleteByCartId(@Param("cart_id") int cartId);
	
}
