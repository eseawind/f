package com.f.services.goods;

import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;

public interface IGoods {

	
	/**
	 * 
	 * 现在商品及规格商品
	 * 
	 * */
	public void insertGoodsInfo(Goods goods,CGoods cg,GCategory gc,GStock gs);
	
	public void updateGoodsInfo(Goods goods,CGoods cg,GCategory gc,GStock gs);
	
	public void insertCGoodsInfo(CGoods cg, GStock gs);
}
