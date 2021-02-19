package com.ga.marketcom.dao;
import org.springframework.data.repository.CrudRepository;
import com.ga.marketcom.model.ProductImage;

public interface ProductImageDao extends CrudRepository<ProductImage,Integer> {
	
	public ProductImage findById(int id);
	
}
