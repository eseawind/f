package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Settlements implements Serializable{

	private static final long serialVersionUID = 2987345994051077155L;
	private List<Settlement> settlements = new ArrayList<Settlement>();
	//商品总金额
	private BigDecimal totalPrice = BigDecimal.ZERO;
	//现金折扣金额 (主要用于满减活动)
	private BigDecimal discountPrice = BigDecimal.ZERO;
	//订单金额
	private BigDecimal orderPrice = BigDecimal.ZERO;
	//是否可以结算
	private boolean isSettle = true;
	
	public void builder(){
		for(Settlement s:this.settlements){
			this.totalPrice = this.totalPrice.add(s.getTotalPrice());
			this.discountPrice = this.discountPrice.add(s.getDiscountPrice());
			this.orderPrice = this.orderPrice.add(s.getOrderPrice());
			this.isSettle = this.isSettle&&s.isSettle();
		}
	}

	public List<Settlement> getSettlements() {
		return settlements;
	}

	public void setSettlements(List<Settlement> settlements) {
		this.settlements = settlements;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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

	public boolean isSettle() {
		return isSettle;
	}

	public void setSettle(boolean isSettle) {
		this.isSettle = isSettle;
	}
}
