package com.f.commons;

public enum EPayType {

	Balance(1,"余额"),
	Alipay(2,"支付宝"),
	Wx(3,"微信");
	private int code;
	private String name;
	EPayType(int code,String name){
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
}
