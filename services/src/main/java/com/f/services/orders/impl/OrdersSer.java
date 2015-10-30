package com.f.services.orders.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.cart.Buyer;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.commons.EPayType;
import com.f.component.orders.IOrdersCom;
import com.f.dto.users.BalanceLog;
import com.f.dto.users.Users;
import com.f.orders.ResOrders;
import com.f.services.orders.IOrders;
import com.f.services.users.IUsers;

import framework.exception.BusinessException;

@Service
public class OrdersSer implements IOrders{
	
	@Autowired
	private IUsers usersSer;
	@Autowired
	private IOrdersCom ordersCom;

	@Override
	public ResOrders insertCommitOrders(Buyer buyer, Settlements settlements) {
		if(!settlements.isSettle()){
			throw new BusinessException(117L);
		}
		//余额支付处理
		if(buyer.getPayType() == EPayType.Balance.getCode()){
			Users users = usersSer.selectMUsers(buyer.getUserId());
			if(StringUtils.isEmpty(users.getPpassword())||!users.getPpassword().equals(buyer.getPayPassword())){
				throw new BusinessException(130L);
			}
			if(settlements.getOrderPrice().compareTo(users.getBalance()) > 0){
				throw new BusinessException(131L);
			}else{
				if(settlements.getOrderPrice().compareTo(BigDecimal.ZERO) > 0){
					BalanceLog log = new BalanceLog();
					log.setAfterBalance(users.getBalance().subtract(settlements.getOrderPrice()));
					log.setMoney(settlements.getOrderPrice());
					log.setOperName(users.getUsername());
					log.setPlatform(1);
					log.setType(2);
					log.setUserId(buyer.getUserId());
					usersSer.updateMUsersBalance(buyer.getUserId(), settlements.getOrderPrice().negate(), log);
				}
			}
		}
		ResOrders resOrders = new ResOrders();
		for(Settlement settlement:settlements.getSettlements()){
			if(settlement.isSettle()&&settlement.isHasChecked()){
				buyer.setCurMerchantId(settlement.getMerchantId());
				resOrders.getResOrders().add(ordersCom.insertOrders(buyer, settlement));
			}
		}
		ordersCom.updateStockByOrders(settlements);
		return resOrders;
	}

}
