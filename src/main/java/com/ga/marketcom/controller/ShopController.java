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
	public Shop addShop(@RequestBody Shop shop, @RequestParam int id) {
		User user = userDao.findById(id);
		// Add a new shop if the user does not have a shop already
		// and if the user has shop_owner role
		if (user.getShop() == null && user.getUserRole().equals("ROLE_OWNER")) {
			user.setShop(shop);
			shop.setUser(user);
			shopDao.save(shop);
			userDao.save(user);
			return shop;
		}
		return null;
	}

	// Return all shops
	@GetMapping("/shop/index")
	public Shop getShop(@RequestParam int id) {
		User user = userDao.findById(id);
		if(user.getUserRole().equals("ROLE_OWNER")) {
			var it=user.getShop();
			return it;
		}
		System.out.println(user.getShop());
		var it = shopDao.findAll();
		return (Shop) it;
	}

	// Return a shop of a specific shop owner
	@GetMapping("/shop/detail")
	public Shop shopDetails(@RequestParam int userId) {
		User user = userDao.findById(userId);
		return user.getShop();
	}

	// Edit a shop of a specific shop owner
	@PutMapping("/shop/edit")
	public Shop editShop(@RequestBody Shop shop) {
		
			shopDao.save(shop);
			return shop;
		
	}

	// Delete a shop of a specific shop owner
	@DeleteMapping("/shop/delete")
	public boolean deleteShop(@RequestParam int id) {
//		User user=new User();
//		user=userDao.findByShop(id);
		//user.setShop(null);
		shopDao.deleteById(id);
		return true;
	}

}
