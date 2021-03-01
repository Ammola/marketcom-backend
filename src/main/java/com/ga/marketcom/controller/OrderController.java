package com.ga.marketcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.marketcom.dao.OrderDao;
import com.ga.marketcom.dao.OrderProductDao;
import com.ga.marketcom.dao.ProductDao;
import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.Order;
import com.ga.marketcom.model.OrderProduct;
import com.ga.marketcom.model.Product;
import com.ga.marketcom.model.User;

//@CrossOrigin(origins = "*")
@RestController
public class OrderController {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderProductDao orderProductDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	// Add a shop for a specific shop owner
		@PostMapping("/order/add")
		public Order addOrder(@RequestBody Order order, @RequestParam int userId) {
			System.out.println("Add order");
			User user = userDao.findById(userId);
			Order savedOrder = orderDao.save(order);
			savedOrder.setUser(user);
			orderDao.save(order);
			return order;
		}
		
		@PostMapping("/product-to-order/add")
		public OrderProduct addOrderProduct(@RequestBody OrderProduct orderProduct, @RequestParam int productId, @RequestParam int orderId) {
			System.out.println("Add order product");
			Product product = productDao.findById(productId);
			OrderProduct savedOrderProduct = orderProductDao.save(orderProduct);
			savedOrderProduct.setProduct(product);
			Order order = orderDao.findById(orderId);
			savedOrderProduct.setOrder(order);
			orderProductDao.save(orderProduct);
			return orderProduct;
		}
		

}
