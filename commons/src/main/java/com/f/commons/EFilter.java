package com.f.commons;

public enum EFilter {

	/**
	 * 商品查询过滤状态state = 1
	 * */
	state_1(1),
	/**
	 * 商品查询过滤状态state = 1|2
	 * */
	state_3(3);
	private int code;
	EFilter(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	
	public static EFilter getEFilter(int code){
		EFilter filter = null;
		switch(code){
		case 1:filter = EFilter.state_1;break;
		case 3:filter = EFilter.state_3;break;
		}
		return filter;
	}
}
