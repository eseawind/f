package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SettleCart implements Serializable{

	private static final long serialVersionUID = -1599236470983369139L;
	public static final int NORMAL = 1;
	public static final int PACKAGE = 2;
	public static final int OTHERS = 3;

	private List<SettleGoods> settleGoodsList = new ArrayList<SettleGoods>();
	private String settleCartStr;
	private int number = 1;
	private int type = NORMAL;
	private boolean checked;
	private BigDecimal totalPrice = BigDecimal.ZERO;
	private BigDecimal discountPrice = BigDecimal.ZERO;
	private BigDecimal orderPrice = BigDecimal.ZERO;
	
	public List<SettleGoods> getSettleGoodsList() {
		return settleGoodsList;
	}
	public void setSettleGoodsList(List<SettleGoods> settleGoodsList) {
		this.settleGoodsList = settleGoodsList;
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
		if(this.type == OTHERS){
			this.settleCartStr = "";
			return;
		}
		Collections.sort(this.settleGoodsList, new Comparator<SettleGoods>(){
			public int compare(SettleGoods o1, SettleGoods o2) {
				return o1.getCgid().compareTo(o2.getCgid());
			}
		});
		StringBuilder sb = new StringBuilder(this.checked?Cart.SEPARTOR_Y:Cart.SEPARTOR_N);
		sb.append(Cart.SEPARATOR_1);
		SettleGoods sg = null;
		for(int i=0,j=this.settleGoodsList.size();i<j;i++){
			sg = this.settleGoodsList.get(i);
			sb.append(sg.getCgid());
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
		if(this.type == OTHERS){
			return "";
		}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
