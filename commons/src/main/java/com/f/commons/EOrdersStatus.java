package com.f.commons;

public enum EOrdersStatus {

	Status_1(1,"未确认"),
	Status_2(2,"确认"),
	Status_3(3,"处理中"),
	Status_4(4,"已发货"),
	Status_5(5,"确认收货"),
	Status_6(6,"退货中"),
	Status_7(7,"退货完成");
	private int code;
	private String name;
	EOrdersStatus(int code,String name){
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
