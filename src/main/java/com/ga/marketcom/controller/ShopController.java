package com.ga.marketcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ga.marketcom.dao.ShopDao;
import com.ga.marketcom.dao.UserDao;
import com.ga.marketcom.model.Shop;
import com.ga.marketcom.model.User;

@RestController
public class ShopController {

	@Autowired
	private ShopDao shopDao;

	@Autowired
	private UserDao userDao;

	// Add a shop for a specific shop owner
	@PostMapping("/shop/add")
	public Shop addShop(@RequestBody Shop shop, @RequestParam int userId) {
		User user = userDao.findById(userId);
		// Add a new shop if the user does not have a shop already
		// and if the user has shop_owner role
		if (user.getShop() == null && user.getUserRole().equals("ROLE_OWNER")) {
			user.setShop(shop);
			shop.setUser(user);
			shopDao.save(shop);
			userDao.save(user);
			return user.getShop();
		}
		return null;
	}

	// Return all shops
	@GetMapping("/shop/index")
	public Iterable<Shop> getShop() {
		var it = shopDao.findAll();
		return it;
	}

	// Return a shop of a specific shop owner
	@GetMapping("/shop/detail")
	public Shop shopDetails(@RequestParam int userId) {
		User user = userDao.findById(userId);
		return user.getShop();
	}

	// Edit a shop of a specific shop owner
	@PutMapping("/shop/edit")
	public Shop editShop(@RequestBody Shop shop, @RequestParam int userId) {
		User user = userDao.findById(userId);
		if(user.getShop() != null) {
			shop.setId(user.getShop().getId());
			user.setShop(shop);
			userDao.save(user);
			shopDao.save(shop);
			return user.getShop();
		}
		return null;
	}

	// Delete a shop of a specific shop owner
	@DeleteMapping("/shop/delete")
	public Shop deleteShop(@RequestParam int userId) {
		User user = userDao.findById(userId);
		Shop shop = user.getShop(); 
		user.setShop(null);
		int shopId = shop.getId();
		shopDao.deleteById(shopId);
		return shop;
	}

}
