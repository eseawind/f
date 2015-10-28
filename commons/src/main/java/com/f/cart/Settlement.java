package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import framework.exception.BusinessException;

public class Settlement implements Serializable{

	private static final long serialVersionUID = -9097201469029330538L;

	//购物车对象
	private List<SettleCart> settleCarts = new ArrayList<SettleCart>();
	//商品总金额
	private BigDecimal totalPrice = BigDecimal.ZERO;
	//现金折扣金额 (主要用于满减活动)
	private BigDecimal discountPrice = BigDecimal.ZERO;
	//订单金额
	private BigDecimal orderPrice = BigDecimal.ZERO;
	//是否可以结算
	private boolean isSettle = true;
	
	//该商户下边是否存在勾选的商品
	private boolean hasChecked = false;
	
	private String reason;
	
	private Long merchantId;
	
	private String merchantName;
	
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
	public void builder(){
		for(SettleCart sc:this.settleCarts){
			if(sc.isChecked()){
				if(sc.getType() == SettleCart.OTHERS){
					this.discountPrice = this.discountPrice.add(sc.getDiscountPrice());
				}else{
					this.totalPrice = this.totalPrice.add(sc.getTotalPrice());
					this.orderPrice = this.orderPrice.add(sc.getOrderPrice());
					if(!this.hasChecked){
						this.hasChecked = true;
					}
				}
				
				for(SettleGoods sg:sc.getSettleGoodsList()){
					SSettleGoods ssg = ((SSettleGoods) sg);
					if(sg.getStockNum() < sc.getNumber()){
						this.isSettle = false;
						ssg.setRemark(BusinessException.getMessage(118L, sg.getGname(),sg.getCgname(),sg.getStockNum()));
					}
					if(sg.getState().intValue() != 1){
						this.isSettle = false;
						ssg.setRemark(BusinessException.getMessage(120L));
					}
				}
			}
		}
		if(!this.isSettle){
			this.reason = BusinessException.getMessage(119L);
		}
		if(this.settleCarts.size() > 0){
			SettleCart sc = this.settleCarts.get(this.settleCarts.size() - 1);
			this.merchantId = sc.getSettleGoodsList().get(0).getMerchantId();
			this.merchantName = sc.getSettleGoodsList().get(0).getMerchantName();
		}
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=this.settleCarts.size();i<j;i++){
			sb.append(this.settleCarts.get(i).toString());
			if(i<j-1){
				sb.append(Carts.SEPARATOR_1);
			}
		}
		return sb.toString();
	}
	public boolean isSettle() {
		return isSettle;
	}
	public void setSettle(boolean isSettle) {
		this.isSettle = isSettle;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public boolean isHasChecked() {
		return hasChecked;
	}
	public void setHasChecked(boolean hasChecked) {
		this.hasChecked = hasChecked;
	}
	
}
