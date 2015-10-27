package com.f.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.commons.Combobox;
import com.f.dao.ext.BrandMapperExt;
import com.f.dao.goods.BrandMapper;
import com.f.dto.goods.Brand;
import com.f.dto.goods.BrandExample;
import com.f.services.IBrand;

import framework.exception.BusinessException;
import framework.web.Pager;

@Service
public class BrandSer implements IBrand {

	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private BrandMapperExt brandMapperExt;
	@Override
	public Pager<List<Brand>> selectBrands(Long merchant, String name, int page, int rows) {
		BrandExample e = new BrandExample();
		BrandExample.Criteria bc = e.createCriteria().andMerchantIdEqualTo(merchant);
		if(StringUtils.isNotEmpty(name)){
			bc.andNameLike("%"+name+"%");
		}else{
			name = null;
		}
		List<Brand> list = brandMapperExt.selBrand(merchant, name, (page-1)*rows, rows);
		long count = brandMapper.countByExample(e);
		return new Pager<List<Brand>>(list,count);
	}

	@Override
	public List<Combobox> selectCombobox(Long merchant) {
		BrandExample e = new BrandExample();
		e.createCriteria().andMerchantIdEqualTo(merchant).andIsUseEqualTo(1);
		List<Brand> list = brandMapper.selectByExample(e);
		List<Combobox> res = new ArrayList<Combobox>(list.size());
		Combobox combobox=null;
		for(Brand b:list){
			combobox = new Combobox();
			combobox.setId(b.getId());
			combobox.setK(b.getName());
			combobox.setV(b.getId().toString());
			res.add(combobox);
		}
		return res;
	}

	@Override
	public void insertBrand(Brand brand) {
		brand.setCreatetime(new Date());
		int i = brandMapper.insertSelective(brand);
		if(i != 1){
			throw new BusinessException(110L);
		}
	}

	@Override
	public void updateBrand(Brand brand) {
		int i = brandMapperExt.updBrand(brand);
		if(i != 1){
			throw new BusinessException(111L);
		}
	}

}
