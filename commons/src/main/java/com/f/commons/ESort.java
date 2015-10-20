package com.f.commons;

public enum ESort {
	
	def(0),
	price_asc(1),
	price_desc(2),
	stock_asc(3),
	stock_desc(4);
	private int code;
	ESort(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	
	public static ESort getESort(int code){
		ESort sort = null;
		switch(code){
		case 0:sort = ESort.def;break;
		case 1:sort = ESort.price_asc;break;
		case 2:sort = ESort.price_desc;break;
		case 3:sort = ESort.stock_asc;break;
		case 4:sort = ESort.stock_desc;break;
		}
		return sort;
	}
}
