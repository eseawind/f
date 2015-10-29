package com.f.orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResOrders implements Serializable{

	private static final long serialVersionUID = -235203158015828061L;
	private List<ResOrder> resOrders = new ArrayList<ResOrder>();
	private BigDecimal payPrice = BigDecimal.ZERO;
	private Integer payType;
	public List<ResOrder> getResOrders() {
		return resOrders;
	}
	public void setResOrders(List<ResOrder> resOrders) {
		this.resOrders = resOrders;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	public void builder(){
		for(ResOrder resOrder:resOrders){
			this.payPrice = this.payPrice.add(resOrder.getPayPrice());
		}
	}

}
