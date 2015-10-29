package com.f.component.orders;

import com.f.cart.Buyer;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.orders.ResOrder;

public interface IOrdersCom {
	
	public ResOrder insertOrders(Buyer buyer,Settlement settlement);
	
	public void updateStockByOrders(Settlements settlements);

}
