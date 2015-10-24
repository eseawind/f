package com.f.services.settle;

import java.util.List;
import com.f.cart.Buyer;
import com.f.cart.Carts;
import com.f.cart.Settlement;
import com.f.component.act.IActHandler;

public interface ISettle {
	
	/**
	 * 结算
	 * */
	public Settlement selectSettlement(Buyer buyer,Carts carts);
	
	/**
	 * 得到活动处理IActHandler
	 * */
	public List<IActHandler> selectIActHandler(Buyer buyer,List<Long> cgids);
	
}
