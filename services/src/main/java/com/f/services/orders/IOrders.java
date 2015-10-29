package com.f.services.orders;

import com.f.cart.Buyer;
import com.f.cart.Settlements;
import com.f.orders.ResOrders;

public interface IOrders {

	/**
	 * 提交订单方法
	 * */
	public ResOrders insertCommitOrders(Buyer buyer,Settlements settlements);
}
