package com.f.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.cart.Buyer;
import com.f.cart.CartStr;
import com.f.cart.Carts;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.commons.Constants;
import com.f.commons.User;
import com.f.orders.ResOrders;
import com.f.services.ICarts;
import com.f.services.orders.IOrders;
import com.f.services.settle.ISettle;

import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.session.ISession;

@Controller
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private ISession session;
	@Autowired
	private ICarts cartsSer;
	@Autowired
	private ISettle settleSer;
	@Autowired
	private IOrders orderSer;

	@Channel(Constants.M)
	@RequestMapping("commitOrders.htm")
	@ResponseBody
	public ResBo<?> commitOrders(@ModelAttribute Buyer buyer){
		User user  = (User)session.get(Constants.USERINFO);
		buyer.setUserId(user.getId());
		buyer.setChannel(Constants.M);
		CartStr cs = cartsSer.selectCartStr(user.getId());
		Carts carts = new Carts(cs.getCartStr());
		Settlements settlements = new Settlements();
		if(carts.getCartsSize() > 0){
			Map<Long,Carts> cartsMap= carts.groupByMerchant();
			for(Long merchantId:cartsMap.keySet()){
				buyer.setCurMerchantId(merchantId);
				settlements.getSettlements().add(settleSer.selectSettlement(buyer, cartsMap.get(merchantId)));
			}
			settlements.builder();
		}else{
			settlements.setSettle(false);
		}
		if(settlements.isSettle()){
			return new ResBo<ResOrders>(orderSer.insertCommitOrders(buyer, settlements));
		}
		StringBuilder sb = new StringBuilder();
		for(Settlement settlement:settlements.getSettlements()){
			if(!settlement.isSettle()){
				sb.append(settlement.getReason());
			}
		}
		ResBo<Settlements> resBo = new ResBo<Settlements>(123L,sb.toString());
		resBo.setResult(settlements);
		return resBo;
	}
}
