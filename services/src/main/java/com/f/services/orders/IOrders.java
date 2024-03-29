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
	 * 后台查询订单
	 * */
	public Pager<List<Map<String,Object>>> selectHOrders(Long userId,Long merchantId,String orderNum,Integer isPaid,Integer state,Integer status,Date sdate,Date edate, Integer page, Integer rows);
	
	/**
	 * 根据订单号查询详情
	 * */
	public Map<String,Object> selectODetail(long orderId,Long userId,Long merchantId);
	
	/**
	 * 修改订单状态
	 * */
	public void updateOrders(long orderId,Long userId,Long merchantId,Integer state,Integer status,Integer isPaid);
	
	/**
	 * 批量修改订单状态
	 * */
	public void updateOrdersBatch(List<Long> orderIds,Long userId,Long merchantId,Integer state,Integer status,Integer isPaid);
	
	/**
	 * 商家导出自己的订单包括明细
	 * */
	public List<Map<String,Object>> selectOrdersExcel(long merchantId,Integer isPaid,Integer state,Integer status,Date sdate,Date edate);
}
