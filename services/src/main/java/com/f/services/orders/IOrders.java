package com.f.services.orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.f.cart.Buyer;
import com.f.cart.Settlements;
import com.f.orders.ResOrders;

import framework.web.Pager;

public interface IOrders {

	/**
	 * 提交订单方法
	 * */
	public ResOrders insertCommitOrders(Buyer buyer,Settlements settlements);
	
	/**
	 * 查询订单
	 * 
	 * */
	public Pager<List<Map<String,Object>>> selectOrders(Long userId,Long merchantId,String orderNum,Integer isPaid,Integer state,Integer status,Date sdate,Date edate, int page, int rows);
	
	/**
	 * 根据订单号查询详情
	 * */
	public Map<String,Object> selectODetail(long orderId,Long userId,Long merchantId);
}
