package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Settlement implements Serializable{

	private static final long serialVersionUID = -9097201469029330538L;

	//购物车对象
	private List<SettleCart> settleCarts = new ArrayList<SettleCart>();
	//商品总金额
	private BigDecimal totalPrice = BigDecimal.ZERO;
	//现金折扣金额
	private BigDecimal discountPrice = BigDecimal.ZERO;
	//订单金额
	private BigDecimal orderPrice = BigDecimal.ZERO;
	
	public List<SettleCart> getSettleCarts() {
		return settleCarts;
	}
	public void setSettleCarts(List<SettleCart> settleCarts) {
		this.settleCarts = settleCarts;
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
	
}
