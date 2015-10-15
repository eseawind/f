package com.f.services.goods.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.dao.goods.CGoodsMapper;
import com.f.dao.goods.GCategoryMapper;
import com.f.dao.goods.GStockMapper;
import com.f.dao.goods.GoodsMapper;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

public class GoodsSer implements IGoods{
	
	@Autowired
	private GoodsMapper gmapper;
	@Autowired
	private CGoodsMapper cgmapper;
	@Autowired
	private GCategoryMapper gcmapper;
	@Autowired
	private GStockMapper gsmapper;
	

	@Override
	public void insertOrUpdGoodsInfo(Goods goods, CGoods cg, GCategory gc,
			GStock gs) {
		
	}

}
