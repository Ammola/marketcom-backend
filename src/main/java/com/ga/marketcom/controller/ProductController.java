package com.ga.marketcom.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ga.marketcom.dao.ProductDao;
import com.ga.marketcom.dao.ShopDao;
import com.ga.marketcom.model.Product;
import com.ga.marketcom.model.Shop;

@RestController
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ShopDao shopDao;

	// HTTP POST REQUEST - Product Add
	@PostMapping("/product/add")
	public Product addProduct(@RequestBody Product product, @RequestParam int shopId) {
		Shop shop = shopDao.findById(shopId);
		Set<Product> shopProducts = new HashSet<Product>();
		shopProducts = shop.getProducts();
		shopProducts.add(product);
		shop.setProducts(shopProducts);
		product.setShop(shop);
		productDao.save(product);
		shopDao.save(shop);
		return product;
	}

	// Return all products of a specific shop
	@GetMapping("/product/index")
	public Iterable<Product> getProducts(@RequestParam int shopId) {
		Shop shop = shopDao.findById(shopId);
		return shop.getProducts();

	}

	// Return a product
	@GetMapping("/product/detail")
	public Product getProductDetail(@RequestParam int productId) {
		Product product = productDao.findById(productId);
		return product;
	}

	// Edit a product of a specific shop
	@PutMapping("/product/edit")
	public Product editProduct(@RequestBody Product newProduct, @RequestParam int shopId) {
		Shop shop = shopDao.findById(shopId);
		Set<Product> allProducts = new HashSet<Product>();
		allProducts = shop.getProducts();
		allProducts.remove(newProduct);
		allProducts.add(newProduct);
		shop.setProducts(allProducts);
		newProduct.setShop(shop);
		productDao.save(newProduct);
		shopDao.save(shop);
		return newProduct;
	}
	
	// Edit a product of a specific shop
		@DeleteMapping("/product/delete")
		public Product deleteProduct(@RequestParam int productId) {
			Product product = productDao.findById(productId);
			Shop shop = product.getShop();
			Set<Product> allProducts = new HashSet<Product>();
			allProducts = shop.getProducts();
			allProducts.remove(product);
			shop.setProducts(allProducts);
			productDao.deleteById(productId);
			shopDao.save(shop);
			return product;
		}

}
