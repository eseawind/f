package com.f.services.goods.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.dao.ext.goods.DynpageMapperExt;
import com.f.dao.goods.DcgoodsMapper;
import com.f.dao.goods.DynpageMapper;
import com.f.dto.goods.Dcgoods;
import com.f.dto.goods.Dynpage;
import com.f.services.goods.IDynpage;

import framework.web.Pager;

@Service
public class DynpageSer implements IDynpage{
	
	@Autowired
	private DynpageMapperExt dynext;
	@Autowired
	private DynpageMapper dmapper;
	@Autowired
	private DcgoodsMapper dcgmapper;

	@Override
	public Pager<List<Map<String, Object>>> selectDynpage(Long merchantId,
			Long pageId, Integer type, int page, int rows) {
		List<Map<String,Object>> list = dynext.selectDynpage(merchantId, pageId, type, (page-1)*rows, rows);
		long count = dynext.countDynpage(merchantId, pageId, type);
		return new Pager<List<Map<String,Object>>>(list,count);
	}

	@Override
	public Pager<List<Long>> selectDcgoods(Long merchantId, Long pageId,
			Integer type, int page, int rows) {
		List<Long> list = dynext.selectDcgoods(merchantId, pageId, type, (page-1)*rows, rows);
		long count = dynext.countDynpage(merchantId, pageId, type);
		return new Pager<List<Long>>(list,count);
	}

	@Override
	public void insertDynpage(Dynpage page, List<Dcgoods> list) {
		dynext.insertDynpage(page);
		for(Dcgoods dcg:list){
			dcg.setPageId(page.getId());
		}
		dynext.insertDcgoods(list);
	}

	@Override
	public void updateDynpage(Dynpage page) {
		page.setMerchantId(null);
		dmapper.updateByPrimaryKeySelective(page);
	}

	@Override
	public void updateDcgoods(Dcgoods dcg) {
		dcg.setPageId(null);
		dcgmapper.updateByPrimaryKeySelective(dcg);
	}

}
