package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SettleCart implements Serializable{

	private static final long serialVersionUID = -1599236470983369139L;

	private List<SettleGoods> cartGoodsList = new ArrayList<SettleGoods>();
	private String settleCartStr;
	private int number = 1;
	private BigDecimal totalPrice = BigDecimal.ZERO;
	private BigDecimal discountPrice = BigDecimal.ZERO;
	private BigDecimal orderPrice = BigDecimal.ZERO;
	
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
	
	public String getSettleCartStr(){
		return this.settleCartStr;
	}
	
	public void builder(){
		Collections.sort(this.cartGoodsList, new Comparator<SettleGoods>(){
			public int compare(SettleGoods o1, SettleGoods o2) {
				return o1.getCgid().compareTo(o2.getCgid());
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=this.cartGoodsList.size();i<j;i++){
			sb.append(this.cartGoodsList.get(i).getCgid());
			if(i < j-1){
				sb.append(Cart.SEPARATOR_2);
			}
		}
		this.settleCartStr = sb.toString();
	}
	
	public String toCartString(){
		if(this.settleCartStr == null){
			builder();
		}
		return this.settleCartStr;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(toCartString());
		sb.append(Cart.SEPARATOR_1);
		sb.append(this.number);
		return sb.toString();
	}
	
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
}
