package com.f.orders;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResOrder implements Serializable{

	private static final long serialVersionUID = -235203158015828061L;

	private Long orderId;
	private String orderNum;
	private BigDecimal payPrice = BigDecimal.ZERO;
	private Long merchantId;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
