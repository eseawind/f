package com.f.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SSettleGoods extends SettleGoods{

	private static final long serialVersionUID = 3958079070643142330L;
	
	private boolean referenced = false;
	private int buyNum;
	private boolean checked = true;

	//主要用于提示库存不足
	private String remark;
	@JsonIgnore
	public int getBuyNum() {
		return buyNum;
	}
	
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JsonIgnore
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	@Override
	public SSettleGoods clone(){
		SSettleGoods ssg = new SSettleGoods();
		ssg.setGid(this.getGid());
		ssg.setCgid(this.getCgid());
		ssg.setStockNum(this.getStockNum());
		ssg.setPrice(this.getPrice());
		ssg.setPhoto(this.getPhoto());
		ssg.setGname(this.getGname());
		ssg.setCgname(this.getCgname());
		ssg.setMerchantId(this.getMerchantId());
		ssg.setMerhantName(this.getMerhantName());
		ssg.setBrandId(this.getBrandId());
		ssg.setCode(this.getCode());
		ssg.setReferenced(this.isReferenced());
		ssg.setBuyNum(this.getBuyNum());
		ssg.setChecked(this.isChecked());
		return ssg;
	}
	@JsonIgnore
	public boolean isReferenced() {
		return referenced;
	}

	public void setReferenced(boolean referenced) {
		this.referenced = referenced;
	}
	
}
