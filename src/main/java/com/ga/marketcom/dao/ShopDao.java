package com.ga.marketcom.dao;
import org.springframework.data.repository.CrudRepository;
import com.ga.marketcom.model.Shop;

public interface ShopDao extends CrudRepository<Shop,Integer> {
	
	public Shop findById(int id);
	
}
