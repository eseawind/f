package com.f.dao.ext.orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.f.dto.orders.ODetail;
import com.f.dto.orders.OPayType;
import com.f.dto.orders.Orders;

public interface OrdersMapperExt {

	public int insertOrders(Orders orders);

	public int insertOrderDetail(List<ODetail> list);

	public int insertOPayType(OPayType opt);

	public void updateCGoodsStock(List<Map<String, Object>> list);

	public List<Map<String, Object>> selectOrders(@Param("userId") Long userId,
			@Param("merchantId") Long merchantId,
			@Param("orderNum") String orderNum,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status, @Param("sdate") Date sdate,
			@Param("edate") Date edate, @Param("start") int start,
			@Param("rows") int rows);

	public long countOrders(@Param("userId") Long userId,
			@Param("merchantId") Long merchantId,
			@Param("orderNum") String orderNum,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status, @Param("sdate") Date sdate,
			@Param("edate") Date edate);

	public List<Map<String, Object>> selectODetailByOrderIds(List<Long> orderIds);

	public List<Map<String, Object>> selectODetail(
			@Param("orderId") long orderId, @Param("userId") Long userId,
			@Param("merchantId") Long merchantId);

	public int updateOrders(@Param("orderId") long orderId,
			@Param("userId") Long userId, @Param("merchantId") Long merchantId,
			@Param("state") Integer state, @Param("status") Integer status,
			@Param("isPaid") Integer isPaid);

	public int updateOrdersBatch(@Param("os") List<Long> orderIds,
			@Param("userId") Long userId, @Param("merchantId") Long merchantId,
			@Param("state") Integer state, @Param("status") Integer status,
			@Param("isPaid") Integer isPaid);

	public List<Map<String, Object>> excelOrders(
			@Param("merchantId") Long merchantId,
			@Param("isPaid") Integer isPaid, @Param("state") Integer state,
			@Param("status") Integer status, @Param("sdate") Date sdate,
			@Param("edate") Date edate);
}
