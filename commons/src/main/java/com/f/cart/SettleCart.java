package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SettleCart implements Serializable{

	private static final long serialVersionUID = -1599236470983369139L;

	private List<SettleGoods> cartGoodsList = new ArrayList<SettleGoods>();
	private int number = 1;
	private BigDecimal totalPrice = BigDecimal.ZERO;
	public List<SettleGoods> getCartGoodsList() {
		return cartGoodsList;
	}
	public void setCartGoodsList(List<SettleGoods> cartGoodsList) {
		this.cartGoodsList = cartGoodsList;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
