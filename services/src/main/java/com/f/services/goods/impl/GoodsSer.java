package com.f.services.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.commons.EFilter;
import com.f.commons.ESort;
import com.f.commons.GoodsDynInfo;
import com.f.commons.GoodsStaInfo;
import com.f.dao.ext.goods.GoodsMapperExt;
import com.f.dao.goods.CGoodsMapper;
import com.f.dao.goods.GoodsMapper;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

import framework.exception.BusinessException;
import framework.web.Pager;

@Service
public class GoodsSer implements IGoods{
	
	@Autowired
	private GoodsMapper gmapper;
	@Autowired
	private CGoodsMapper cgmapper;
	@Autowired
	private GoodsMapperExt gext;

	@Override
	public void insertGoodsInfo(Goods goods, CGoods cg) {
		Date date = new Date();
		goods.setCreatetime(date);
		cg.setCreatetime(date);
		int i = gext.insertGoods(goods);
		if(i != 1){
			throw new BusinessException(100L);
		}
		cg.setGid(goods.getId());
		i = gext.insertCGoods(cg);
		if(i != 1){
			throw new BusinessException(101L);
		}
	}
	
	@Override
	public void insertCGoodsInfo(CGoods cg) {
		Date date = new Date();
		cg.setCreatetime(date);
		int i = gext.insertCGoods(cg);
		if(i != 1){
			throw new BusinessException(101L);
		}
	}
	
	/**
	 * 用于新增商品时修改商品信息
	 * */
	@Override
	public void updateGoodsInfo(long merchantId, Goods goods, CGoods cg) {
		int i = 0;
		if(goods != null){
			i = gext.updGoods(goods);
			if(i != 1){
				throw new BusinessException(104L);
			}
		}
		if(cg != null){
			i = gext.updCGoods(cg, merchantId);
			if(i != 1){
				throw new BusinessException(105L);
			}
		}
	}

	@Override
	public Pager<List<Map<String, Object>>> selectCGoods(Long cgid,String sku,String gname, Long merchantId, Long brandId,int page,int rows) {
		List<Map<String,Object>> list = gext.selCGoods(cgid, sku, gname, merchantId, brandId, (page-1)*rows, rows);
		long count = gext.countCGoods(cgid, sku, gname, merchantId, brandId);
		return new Pager<List<Map<String, Object>>>(list,count);
	}

	@Override
	public Pager<List<GoodsStaInfo>> selectCGoodsStaInfo(Long merchantId,
			String code, EFilter filter, ESort esort, int page, int rows) {
		List<GoodsStaInfo> list = gext.selectCGoodsStaInfo(merchantId, code, filter.getCode(), esort.getCode(), (page-1)*rows, rows);
		long count = gext.countCGoodsStaInfo(merchantId, code, filter.getCode());
		return new Pager<List<GoodsStaInfo>>(list,count);
	}

	@Override
	public List<GoodsDynInfo> selectCGoodsDynInfo(List<Long> cgids) {
		if(cgids.size() == 0){
			return new ArrayList<GoodsDynInfo>();
		}
		return gext.selectCGoodsDynInfo(cgids);
	}

	@Override
	public List<GoodsStaInfo> selectCGoodsStaInfoByCgIds(List<Long> cgids) {
		if(cgids.size() == 0){
			return new ArrayList<GoodsStaInfo>();
		}
		return gext.selectCGoodsStaInfoByCgIds(cgids);
	}

	@Override
	public List<GoodsStaInfo> selectCGoodsStaInfoByCgId(long cgid) {
		Long gid = gext.selGIdByCgId(cgid);
		if(gid == null){
			return new ArrayList<GoodsStaInfo>();
		}
		return gext.selectCGoodsStaInfoByGId(gid);
	}

}
