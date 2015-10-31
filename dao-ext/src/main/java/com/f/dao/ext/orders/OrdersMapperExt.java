package com.f.dao.ext.orders;

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
	
	public void updateCGoodsStock(List<Map<String,Object>> list);
	
	public List<Map<String,Object>> selectOrders(@Param("userId")Long userId,
			@Param("merchantId")Long merchantId, @Param("orderNum")String orderNum, @Param("isPaid")Integer isPaid, @Param("state")Integer state,
			@Param("status")Integer status, @Param("start")int start, @Param("rows")int rows);
	
	public long countOrders(@Param("userId")Long userId,
			@Param("merchantId")Long merchantId, @Param("orderNum")String orderNum, @Param("isPaid")Integer isPaid, @Param("state")Integer state,
			@Param("status")Integer status);
	
	public List<Map<String,Object>> selectODetail(@Param("orderId")long orderId,@Param("userId")Long userId, @Param("merchantId")Long merchantId);
}
