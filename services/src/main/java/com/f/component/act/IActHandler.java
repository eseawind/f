package com.f.component.act;

import java.util.List;
import com.f.cart.Buyer;
import com.f.cart.SSettleGoods;
import com.f.cart.SettleCart;

public interface IActHandler {

	public int getActCode();
	
	public List<SettleCart> settleCart(Buyer buyer,List<SSettleGoods> ssgs);
	
}
