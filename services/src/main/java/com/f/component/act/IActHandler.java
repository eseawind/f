package com.f.component.act;

import java.util.List;
import java.util.Map;

import com.f.cart.Buyer;
import com.f.cart.SettleCart;
import com.f.cart.SettleGoods;

public interface IActHandler {

	public int getActCode();
	
	public List<SettleCart> settleCart(Buyer buyer,List<Long> cgids,Map<Long,SettleGoods> sgMap);
	
}
