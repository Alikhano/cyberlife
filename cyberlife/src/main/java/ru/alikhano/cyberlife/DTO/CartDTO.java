package ru.alikhano.cyberlife.DTO;

import java.util.Set;

import ru.alikhano.cyberlife.model.CartItem;

public class CartDTO {
	
	private int cartId;
	private double grandTotal;
	private Set<CartItem> items;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	
	

}