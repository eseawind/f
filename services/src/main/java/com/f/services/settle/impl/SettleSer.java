package com.f.services.settle.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.cart.Buyer;
import com.f.cart.Cart;
import com.f.cart.Carts;
import com.f.cart.SSettleGoods;
import com.f.cart.Settlement;
import com.f.component.act.IActHandler;
import com.f.component.act.impl.DefaultActHandler;
import com.f.dao.ext.settle.SettleMapperExt;
import com.f.services.settle.ISettle;

import framework.exception.BusinessException;

/**
 * @author fengmingming 2015年10月24日
 * 
 * */
@Service
public class SettleSer implements ISettle{
	
	@Autowired
	private SettleMapperExt settleExt;

	@Override
	public Settlement selectSettlement(Buyer buyer,Carts carts) {
		Settlement settlement = new Settlement();
		if(carts.getCartsSize() == 0){
			settlement.setSettle(false);
			settlement.setReason(BusinessException.getMessage(117L));
			return settlement;
		}
		List<Long> cgids = new ArrayList<Long>();
		for(Cart cart:carts.getCartList()){
			for(Long cgid:cart.getCgidList()){
				cgids.add(cgid);
			}
		}
		List<SSettleGoods> ssgs = settleExt.selectSSettleGoods(cgids,buyer.getCurMerchantId());
		if(ssgs.size() == 0){
			settlement.setSettle(false);
			settlement.setReason(BusinessException.getMessage(117L));
			return settlement;
		}
		Map<Long, SSettleGoods> ssgMap = new HashMap<Long, SSettleGoods>();
		for(SSettleGoods ssg: ssgs){
			ssgMap.put(ssg.getCgid(), ssg);
		}
		List<SSettleGoods> ssgsAll = new ArrayList<SSettleGoods>();
		SSettleGoods ssg = null;
		SSettleGoods ssgClone = null;
		for(Cart cart:carts.getCartList()){
			for(Long cgid:cart.getCgidList()){
				ssg = ssgMap.get(cgid);
				if(ssg != null){
					if(ssg.isReferenced()){
						ssgClone = ssg.clone();
					}else{
						ssg.setReferenced(true);
						ssgClone = ssg;
					}
					ssgClone.setChecked(cart.isChecked());
					ssgClone.setBuyNum(cart.getNumber());
					ssgsAll.add(ssgClone);
				}
			}
		}
		
		List<IActHandler> actHandlers = selectIActHandler(buyer, cgids);
		actHandlers.add(new DefaultActHandler());
		if(actHandlers.size() > 1){
			Collections.sort(actHandlers, new Comparator<IActHandler>(){

				@Override
				public int compare(IActHandler o1, IActHandler o2) {
					return o1.getActCode()<o2.getActCode()?-1:(o1.getActCode() == o2.getActCode()?0:1);
				}
				
			});
		}
		for(IActHandler actHandler:actHandlers){
			settlement.getSettleCarts().addAll(actHandler.settleCart(buyer, ssgsAll));
		}
		settlement.builder();
		return settlement;
	}

	@Override
	public List<IActHandler> selectIActHandler(Buyer buyer,List<Long> cgids) {
		return new ArrayList<IActHandler>();
	}

}
