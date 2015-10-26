package com.f.component.act.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.f.cart.Buyer;
import com.f.cart.SSettleGoods;
import com.f.cart.SettleCart;
import com.f.component.act.IActHandler;

public class DefaultActHandler implements IActHandler{

	@Override
	public int getActCode() {
		return Integer.MAX_VALUE;
	}

	@Override
	public List<SettleCart> settleCart(Buyer buyer, List<SSettleGoods> ssgs) {
		List<SettleCart> scs = new ArrayList<SettleCart>();
		SettleCart sc = null;
		for(SSettleGoods ssg:ssgs){
			sc = new SettleCart();
			sc.getSettleGoodsList().add(ssg);
			sc.setChecked(ssg.isChecked());
			sc.setNumber(ssg.getBuyNum());
			sc.setTotalPrice(ssg.getPrice().multiply(BigDecimal.valueOf(ssg.getBuyNum())));
			sc.setOrderPrice(sc.getTotalPrice());
			sc.builder();
			scs.add(sc);
		}
		return scs;
	}

}
