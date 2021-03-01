package com.ga.marketcom.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Orders")
@JsonIgnoreProperties(value= {"user"})
public class Order {
	@Id
	@GeneratedValue
	private int id;
	
	private String orderId;
	private String payerId;
	private String paymentId;
	private String billingToken;
	private String facilitatorAccessToken;
	private String amount;
	
	@Column(name="createdAt", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createAt;
	@Column(name="updatedat", nullable = false, updatable = true)
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_user_Id")
	private User user;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderProduct> orderProducts;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "product_order",
//			   joinColumns = { @JoinColumn(name = "order_id") },
//			   inverseJoinColumns = { 
//					   @JoinColumn(name = "product_id")
//					   })
//	private Set<Product> orderedProducts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getBillingToken() {
		return billingToken;
	}

	public void setBillingToken(String billingToken) {
		this.billingToken = billingToken;
	}

	public String getFacilitatorAccessToken() {
		return facilitatorAccessToken;
	}

	public void setFacilitatorAccessToken(String facilitatorAccessToken) {
		this.facilitatorAccessToken = facilitatorAccessToken;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
//	public Set<Product> getOrderedProducts() {
//		return orderedProducts;
//	}
//
//	public void setOrderedProducts(Set<Product> orderedProducts) {
//		this.orderedProducts = orderedProducts;
//	}
	
}
