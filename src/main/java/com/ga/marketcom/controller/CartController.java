package com.ga.marketcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.marketcom.dao.CartDao;
import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.Shop;
import com.ga.marketcom.model.User;

@RestController
public class CartController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	// Add a shop for a specific shop owner
		@PostMapping("/cart/add")
		public Shop addShop(@RequestParam int productId, @RequestParam int cartId) {
//			User user = userDao.findById(id);
//			// Add a new shop if the user does not have a shop already
//			// and if the user has shop_owner role
//			if (user.getShop() == null && user.getUserRole().equals("ROLE_OWNER")) {
//				user.setShop(shop);
//				shop.setUser(user);
//				shopDao.save(shop);
//				userDao.save(user);
//				return shop;
//			}
			return null;
		}

}
