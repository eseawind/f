package com.f.commons;

public enum EIsPaid {

	YES(2,"已支付"),
	NO(1,"未支付");
	private int code;
	private String name;
	EIsPaid(int code,String name){
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
