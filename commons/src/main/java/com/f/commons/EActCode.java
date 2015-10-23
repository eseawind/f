package com.f.commons;

public enum EActCode {

	COUPON(11,"优惠券");

	private int actCode;
	private String actName;
	EActCode(int actCode,String actName){
		this.actCode = actCode;
		this.actName = actName;
	}
	public int getActCode() {
		return actCode;
	}
	public void setActCode(int actCode) {
		this.actCode = actCode;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
}
