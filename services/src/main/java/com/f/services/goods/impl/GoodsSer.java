package com.f.services.goods.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.dao.ext.goods.GoodsMapperExt;
import com.f.dao.goods.CGoodsMapper;
import com.f.dao.goods.GCategoryMapper;
import com.f.dao.goods.GStockMapper;
import com.f.dao.goods.GoodsMapper;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

import framework.exception.BusinessException;

@Service
public class GoodsSer implements IGoods{
	
	@Autowired
	private GoodsMapper gmapper;
	@Autowired
	private CGoodsMapper cgmapper;
	@Autowired
	private GCategoryMapper gcmapper;
	@Autowired
	private GStockMapper gsmapper;
	@Autowired
	private GoodsMapperExt gext;

	@Override
	public void insertGoodsInfo(Goods goods, CGoods cg, GCategory gc, GStock gs) {
		Date date = new Date();
		goods.setCreatetime(date);
		cg.setCreatetime(date);
		gc.setCreatetime(date);
		int i = gext.insertGoods(goods);
		if(i != 1){
			throw new BusinessException(100L);
		}
		cg.setGid(goods.getId());
		i = gext.insertCGoods(cg);
		if(i != 1){
			throw new BusinessException(101L);
		}
		gc.setGid(goods.getId());
		i = gext.insertGCategory(gc);
		if(i != 1){
			throw new BusinessException(102L);
		}
		gs.setCgid(cg.getId());
		i = gext.insertGStock(gs);
		if(i != 1){
			throw new BusinessException(103L);
		}
	}
	
	@Override
	public void insertCGoodsInfo(CGoods cg, GStock gs) {
		Date date = new Date();
		cg.setCreatetime(date);
		int i = gext.insertCGoods(cg);
		if(i != 1){
			throw new BusinessException(101L);
		}
		gs.setCgid(cg.getId());
		i = gext.insertGStock(gs);
		if(i != 1){
			throw new BusinessException(103L);
		}
	}
	
	/**
	 * 用于新增商品时修改商品信息
	 * */
	@Override
	public void updateGoodsInfo(Goods goods, CGoods cg, GCategory gc, GStock gs) {
		int i = 0;
		if(goods != null){
			i = gmapper.updateByPrimaryKeySelective(goods);
			if(i != 1){
				throw new BusinessException(104L);
			}
		}
		if(cg != null){
			i = cgmapper.updateByPrimaryKeySelective(cg);
			if(i != 1){
				throw new BusinessException(105L);
			}
		}
		if(gc != null){
			i = gcmapper.updateByPrimaryKeySelective(gc);
			if(i != 1){
				throw new BusinessException(106L);
			}
		}
		if(gs != null){
			i = gsmapper.updateByPrimaryKeySelective(gs);
			if(i != 1){
				throw new BusinessException(107L);
			}
		}
	}

}
