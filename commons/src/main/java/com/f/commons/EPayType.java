package com.f.commons;

import framework.exception.BusinessException;

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
	
	public static EPayType getEPayType(int code){
		switch(code){
		case 1:return Balance;
		case 2:return Alipay;
		case 3:return Wx;
		}
		throw new BusinessException(128L, code);
	}
	
}
