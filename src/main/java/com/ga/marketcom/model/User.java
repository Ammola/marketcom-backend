package com.ga.marketcom.model;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String city;
	private String country;
	private String street;
	private String zipCode;
	private String resetPassword;
	private int phoneNumber;
	private String userRole;
	
	@Column(name="createdAt", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createAt;
	@Column(name="updatedat", nullable = false, updatable = true)
	@UpdateTimestamp
	private LocalDateTime updateAt;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Shop_Id", referencedColumnName = "id")
    private Shop shop;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cart_Id", referencedColumnName = "id")
    private Cart cart;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Order> orders;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
//	public Set<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
//	

}
