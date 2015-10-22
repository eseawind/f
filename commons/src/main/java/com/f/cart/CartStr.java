package com.f.cart;

import java.io.Serializable;

public class CartStr implements Serializable{

	private static final long serialVersionUID = -8504915972337236995L;

	private Long userId;
	private String cartStr;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCartStr() {
		return cartStr;
	}
	public void setCartStr(String cartStr) {
		this.cartStr = cartStr;
	}
	
}
