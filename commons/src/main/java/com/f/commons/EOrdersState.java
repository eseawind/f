package com.f.commons;

public enum EOrdersState {

	State_1(1,"正常"),
	State_2(2,"取消"),
	State_3(3,"异常");
	private int code;
	private String name;
	EOrdersState(int code,String name){
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
