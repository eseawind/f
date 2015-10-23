package com.f.services.settle;

import com.f.cart.Carts;
import com.f.cart.Settlement;

public interface ISettle {

	/**
	 * 结算
	 * */
	public Settlement selectSettlement(Carts carts);
	
}
