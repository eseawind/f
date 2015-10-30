package com.f.component.orders.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.f.commons.OrdersNumSequence;
import com.f.component.orders.IOrdersCom;
import com.f.dao.ext.orders.OrdersMapperExt;
import com.f.dto.orders.ODetail;
import com.f.dto.orders.OPayType;
import com.f.dto.orders.Orders;
import com.f.orders.ResOrder;

import framework.exception.BusinessException;

@Component
public class OrdersCom implements IOrdersCom{
	
	@Autowired
	private OrdersMapperExt ext;

	@Override
	public ResOrder insertOrders(Buyer buyer, Settlement settle) {
		if(!settle.isSettle()){
			throw new BusinessException(117L);
		}
		Orders orders = new Orders();
		orders.setAreaId(buyer.getAreaId());
		orders.setAreaName(buyer.getAreaName());
		if(buyer.getPayType() == EPayType.Balance.getCode()){
			orders.setBalancePrice(settle.getOrderPrice());
			orders.setIsPaid(EIsPaid.YES.getCode());
			orders.setPaidPrice(settle.getOrderPrice());
			orders.setPayPrice(BigDecimal.ZERO);
		}else{
			orders.setBalancePrice(BigDecimal.ZERO);
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
		orders.setOrderNum(OrdersNumSequence.builderOrdersNum(buyer.getChannel()));
		orders.setOrderPrice(settle.getOrderPrice());
		orders.setProductPrice(settle.getTotalPrice());
		orders.setProvinceId(buyer.getProvinceId());
		orders.setProvinceName(buyer.getProvinceName());
		orders.setRemark(buyer.getRemark());
		orders.setState(EOrdersState.State_1.getCode());
		orders.setStatus(EOrdersStatus.Status_1.getCode());
		orders.setUserId(buyer.getUserId());
		int i = ext.insertOrders(orders);
		if(i != 1){
			throw new BusinessException(125L);
		}
		OPayType opt = new OPayType();
		opt.setOrderId(orders.getId());
		opt.setCode(EPayType.getEPayType(buyer.getPayType()).getCode());
		opt.setName(EPayType.getEPayType(buyer.getPayType()).getName());
		i = ext.insertOPayType(opt);
		if(i != 1){
			throw new BusinessException(129L);
		}
		List<ODetail> ods = new ArrayList<ODetail>();
		for(SettleCart sc:settle.getSettleCarts()){
			if(sc.isChecked()){
				for(SettleGoods sg:sc.getSettleGoodsList()){
					ODetail od = new ODetail();
					od.setBuyPrice(sg.getPrice());
					od.setCgoodsId(sg.getCgid());
					od.setNumber(sc.getNumber());
					od.setSku(sg.getSku());
					od.setOrderId(orders.getId());
					ods.add(od);
				}
			}
		}
		i = ext.insertOrderDetail(ods);
		if(i != ods.size()){
			throw new BusinessException(126L);
		}
		ResOrder resOrder = new ResOrder();
		resOrder.setMerchantId(buyer.getCurMerchantId());
		resOrder.setOrderId(orders.getId());
		resOrder.setOrderNum(orders.getOrderNum());
		resOrder.setPayPrice(orders.getPayPrice());
		return resOrder;
	}

	@Override
	public void updateStockByOrders(Settlements settles) {
		if(settles.isSettle()){
			Map<Long, Integer> stockM = new HashMap<Long, Integer>();
			for(Settlement settle:settles.getSettlements()){
				if(settle.isSettle()&&settle.isHasChecked()){
					for(SettleCart sc:settle.getSettleCarts()){
						if(sc.isChecked()){
							for(SettleGoods sg:sc.getSettleGoodsList()){
								Integer number = stockM.get(sg.getCgid());
								if(number == null){
									stockM.put(sg.getCgid(), sc.getNumber());
								}else{
									stockM.put(sg.getCgid(), number + sc.getNumber());
								}
							}
						}
					}
				}
			}
			if(stockM.size() > 0){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				for(Long cgid:stockM.keySet()){
					Map<String,Object> map = new HashMap<String,Object>(2);
					map.put("cgid", cgid);
					map.put("number", stockM.get(cgid));
					list.add(map);
				}
				ext.updateCGoodsStock(list);
			}else{
				throw new BusinessException(127L);
			}
		}
	}

}
