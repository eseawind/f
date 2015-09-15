package com.f.services.goods;

import java.util.List;
import java.util.Map;

import com.f.commons.ESort;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.Goods;

import framework.web.Pager;

public interface IGoods {

	
	/**
	 * 
	 * 现在商品及规格商品
	 * 
	 * */
	public void insertGoods(Goods goods,List<CGoods> cgs);
	
	/**
	 * 
	 * 单独增加规格商品
	 * 
	 * */
	public void insertCGoods(long merchantId,List<CGoods> cgs);
	
	/**
	 * 修改商品信息
	 * 
	 * */
	public void updateGoods(Goods goods);
	
	/**
	 * 修改规格商品
	 * 
	 * */
	public void updateCGoods(long merchantId,List<CGoods> cgs);
	
	/**
	 * 
	 * 查询商品详情
	 * 
	 * */
	public Map<String,Object> selGoodsDetail(long cgId);
	
	/**
	 * 
	 * 根据商品id列表查询商品信息
	 * 
	 * */
	public List<Map<String,Object>> selGoodsInfo(List<Long> gIds);
	
	/**
	 * 
	 * 商家查询商品信息
	 * 
	 * */
	public Pager<List<Long>> selGoodsIds(long merchantId, Long gId, String gname, Long brand, int page, int rows);
	
	/**
	 * 
	 * 普通用户查询商品信息
	 * 
	 * */
	public Pager<List<Long>> selGoodsIds(String query, String categoryCode, ESort sort, int page, int rows);
}
