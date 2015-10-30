package com.f.dao.ext.orders;

import java.util.List;
import java.util.Map;

import com.f.dto.orders.ODetail;
import com.f.dto.orders.OPayType;
import com.f.dto.orders.Orders;

public interface OrdersMapperExt {

	public int insertOrders(Orders orders);
	
	public int insertOrderDetail(List<ODetail> list);
	
	public int insertOPayType(OPayType opt);
	
	public void updateCGoodsStock(List<Map<String,Object>> list);
}
