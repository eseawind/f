package com.f.component.orders.impl;

import org.springframework.stereotype.Component;

import com.f.cart.Buyer;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.component.orders.IOrdersCom;
import com.f.dto.orders.Orders;
import com.f.orders.ResOrder;

@Component
public class OrdersCom implements IOrdersCom{

	@Override
	public ResOrder insertOrders(Buyer buyer, Settlement settlement) {
		Orders orders = new Orders();
		orders.setAreaId(buyer.getAreaId());
		orders.setAreaName(buyer.getAreaName());
		return null;
	}

	@Override
	public void updateStockByOrders(Settlements settlements) {
		
	}



}
