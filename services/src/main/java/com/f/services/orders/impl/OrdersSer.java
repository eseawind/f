package com.f.services.orders.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.cart.Buyer;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.component.orders.IOrdersCom;
import com.f.orders.ResOrders;
import com.f.services.orders.IOrders;

@Service
public class OrdersSer implements IOrders{
	
	@Autowired
	private IOrdersCom ordersCom;

	@Override
	public ResOrders insertCommitOrders(Buyer buyer, Settlements settlements) {
		ResOrders resOrders = new ResOrders();
		for(Settlement settlement:settlements.getSettlements()){
			buyer.setCurMerchantId(settlement.getMerchantId());
			resOrders.getResOrders().add(ordersCom.insertOrders(buyer, settlement));
		}
		ordersCom.updateStockByOrders(settlements);
		return resOrders;
	}

}
