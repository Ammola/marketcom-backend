package com.ga.marketcom.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ga.marketcom.dao.ProductDao;
import com.ga.marketcom.model.Product;


@RestController
public class ProductController {
	
	@Autowired
	private ProductDao dao;
	
	    // HTTP POST REQUEST - Product Add
		@PostMapping("/product/add")
		public Product addAuthor(@RequestBody Product product) {
			dao.save(product);
			return product;
		}
		
		
		
		
}
