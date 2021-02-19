package com.ga.marketcom.dao;
import org.springframework.data.repository.CrudRepository;
import com.ga.marketcom.model.Product;

public interface ProductDao extends CrudRepository<Product,Integer> {
	
	public Product findById(int id);
	
}
