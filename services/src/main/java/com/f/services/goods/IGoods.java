package com.f.services.goods;

import java.util.List;
import java.util.Map;

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
	
	public void updateGoodsInfo(Goods goods,CGoods cg);
	
	public void insertCGoodsInfo(CGoods cg);
	
	public Pager<List<Map<String,Object>>> selectCGoods(Long cgid,String sku,String gname,Long merchantId,Long brandId,int page,int rows);
}
