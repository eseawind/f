package com.f.component.orders.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.f.cart.Buyer;
import com.f.cart.SettleCart;
import com.f.cart.SettleGoods;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.commons.EIsPaid;
import com.f.commons.EOrdersState;
import com.f.commons.EOrdersStatus;
import com.f.commons.EPayType;
import com.f.component.orders.IOrdersCom;
import com.f.dao.ext.orders.OrdersMapperExt;
import com.f.dto.orders.ODetail;
import com.f.dto.orders.Orders;
import com.f.orders.ResOrder;

@Component
public class OrdersCom implements IOrdersCom{
	
	@Autowired
	private OrdersMapperExt ext;

	@Override
	public ResOrder insertOrders(Buyer buyer, Settlement settle) {
		Orders orders = new Orders();
		orders.setAreaId(buyer.getAreaId());
		orders.setAreaName(buyer.getAreaName());
		if(buyer.getPayType() == EPayType.Balance.getCode()){
			orders.setBalancePrice(settle.getOrderPrice());
			orders.setIsPaid(EIsPaid.YES.getCode());
			orders.setPaidPrice(settle.getOrderPrice());
			orders.setPayPrice(BigDecimal.ZERO);
			
		}else{
			orders.setIsPaid(EIsPaid.NO.getCode());
			orders.setPaidPrice(BigDecimal.ZERO);
			orders.setPayPrice(settle.getOrderPrice());
		}
		orders.setCityId(buyer.getCityId());
		orders.setCityName(buyer.getCityName());
		orders.setConsignee(buyer.getConsignee());
		orders.setDiscountPrice(settle.getDiscountPrice());
		orders.setMerchantId(buyer.getCurMerchantId());
		orders.setMobile(buyer.getMobile());
		orders.setOrderNum("");
		orders.setOrderPrice(settle.getOrderPrice());
		orders.setProductPrice(settle.getTotalPrice());
		orders.setProvinceId(buyer.getProvinceId());
		orders.setProvinceName(buyer.getProvinceName());
		orders.setRemark(buyer.getRemark());
		orders.setState(EOrdersState.State_1.getCode());
		orders.setStatus(EOrdersStatus.Status_1.getCode());
		orders.setUserId(buyer.getUserId());
		ext.insertOrders(orders);
		List<ODetail> ods = new ArrayList<ODetail>();
		for(SettleCart sc:settle.getSettleCarts()){
			if(sc.isChecked()){
				for(SettleGoods sg:sc.getSettleGoodsList()){
					ODetail od = new ODetail();
					od.setBuyPrice(sg.getPrice());
				}
			}
		}
		
		
		return null;
	}

	@Override
	public void updateStockByOrders(Settlements settlements) {
		
	}



}
