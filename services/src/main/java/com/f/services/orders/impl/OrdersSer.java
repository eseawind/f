package com.f.services.orders.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.cart.Buyer;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.commons.EPayType;
import com.f.component.orders.IOrdersCom;
import com.f.dao.ext.orders.OrdersMapperExt;
import com.f.dao.orders.OrdersMapper;
import com.f.dto.orders.Orders;
import com.f.dto.users.BalanceLog;
import com.f.dto.users.Users;
import com.f.orders.ResOrders;
import com.f.services.orders.IOrders;
import com.f.services.users.IUsers;

import framework.exception.BusinessException;
import framework.web.Pager;

@Service
public class OrdersSer implements IOrders{
	
	@Autowired
	private IUsers usersSer;
	@Autowired
	private IOrdersCom ordersCom;
	
	@Autowired
	private OrdersMapperExt oext;
	@Autowired
	private OrdersMapper omapper;

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

	@SuppressWarnings("unchecked")
	@Override
	public Pager<List<Map<String, Object>>> selectOrders(Long userId,
			Long merchantId, String orderNum, Integer isPaid, Integer state,
			Integer status,Date sdate,Date edate, int page, int rows) {
		List<Map<String,Object>> list = oext.selectOrders(userId, merchantId, orderNum, isPaid, state, status,sdate, edate, (page-1)*rows, rows);
		long count = oext.countOrders(userId, merchantId, orderNum, isPaid, state, status, sdate, edate);
		if(list.size() > 0){
			Map<Long, Map<String,Object>> map = new HashMap<Long, Map<String,Object>>();
			List<Long> orderIds = new ArrayList<Long>();
			for(Map<String,Object> m:list){
				m.put("ods", new ArrayList<Map<String,Object>>());
				map.put((Long) m.get("id"), m);
				orderIds.add((Long) m.get("id"));
			}
			List<Map<String,Object>> ods = oext.selectODetailByOrderIds(orderIds);
			for(Map<String, Object> od:ods){
				((List<Map<String,Object>>) map.get(od.get("orderId")).get("ods")).add(od);
			}
		}
		return new Pager<List<Map<String,Object>>>(list,count);
	}

	@Override
	public Map<String, Object> selectODetail(long orderId, Long userId,
			Long merchantId) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		Orders orders = omapper.selectByPrimaryKey(orderId);
		List<Map<String,Object>> list = oext.selectODetail(orderId, userId, merchantId);
		map.put("order", orders);
		map.put("ods", list);
		return map;
	}

	@Override
	public void updateOrders(long orderId, Long userId, Long merchantId,
			Integer state, Integer status, Integer isPaid) {
		oext.updateOrders(orderId, userId, merchantId, state, status, isPaid);
	}

	@Override
	public void updateOrdersBatch(List<Long> orderIds, Long userId,
			Long merchantId, Integer state, Integer status, Integer isPaid) {
		oext.updateOrdersBatch(orderIds, userId, merchantId, state, status, isPaid);
	}

	@Override
	public List<Map<String, Object>> selectOrdersExcel(long merchantId,
			Integer isPaid, Integer state, Integer status, Date sdate,
			Date edate) {
		return oext.excelOrders(merchantId, isPaid, state, status, sdate, edate);
	}

	@Override
	public Pager<List<Map<String, Object>>> selectHOrders(Long userId,
			Long merchantId, String orderNum, Integer isPaid, Integer state,
			Integer status, Date sdate, Date edate, Integer page, Integer rows) {
		Integer start = null;
		if(page != null && rows != null){
			start = (page-1)*rows;
		}
		List<Map<String,Object>> list = oext.selectHOrders(userId, merchantId, orderNum, isPaid, state, status, sdate, edate, start, rows);
		long count = oext.countOrders(userId, merchantId, orderNum, isPaid, state, status, sdate, edate);
		return new Pager<List<Map<String,Object>>>(list, count);
	}

}
