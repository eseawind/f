package com.f.services.goods;

import java.util.List;
import java.util.Map;

import com.f.commons.EFilter;
import com.f.commons.ESort;
import com.f.commons.GoodsDynInfo;
import com.f.commons.GoodsStaInfo;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.Goods;

import framework.web.Pager;

public interface IGoods {

	
	/**
	 * 
	 * 现在商品及规格商品
	 * 
	 * */
	public void insertGoodsInfo(Goods goods,CGoods cg);
	
	public void updateGoodsInfo(long merchantId, Goods goods,CGoods cg);
	
	public void insertCGoodsInfo(CGoods cg);
	
	/**
	 * 商家维护商品信息查询列表
	 * */
	public Pager<List<Map<String,Object>>> selectCGoods(Long cgid,String sku,String gname,Long merchantId,Long brandId,int page,int rows);
	
	/**
	 * 查询商品信息列表
	 * */
	public Pager<List<GoodsStaInfo>> selectCGoodsStaInfo(Long merchantId,String code,EFilter filter,ESort esort,int page,int rows);
	
	/**
	 * 查询商品动态数据，价格，库存
	 * */
	public List<GoodsDynInfo> selectCGoodsDynInfo(List<Long> cgids);
	
	public List<GoodsStaInfo> selectCGoodsStaInfoByCgIds(List<Long> cgids);
	
	/**
	 * 详情页商品信息
	 * */
	public List<GoodsStaInfo> selectCGoodsStaInfoByCgId(long cgid);
	
	/**
	 * 是否收藏
	 * */
	public boolean selectIsCollect(long userId,long cgid);
	/**
	 * 收藏
	 * */
	public void insertCollect(long userId,long cgid);
	
	public void deleteCollect(long userId,long cgid);
	/**
	 * 收藏列表
	 * */
	public Pager<List<Map<String,Object>>> selectCollects(long userId,int page,int rows);
	
}
