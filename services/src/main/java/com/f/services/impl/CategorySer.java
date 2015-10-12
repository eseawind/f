package com.f.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.dao.goods.CategoryMapper;
import com.f.dto.goods.Category;
import com.f.dto.goods.CategoryExample;
import com.f.services.ICategory;

import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class CategorySer implements ICategory{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public ResBo<List<Category>> selectCategories(ReqBo reqBo) {
		CategoryExample e = new CategoryExample();
		e.createCriteria().andFidEqualTo(reqBo.getParamInt("fid"));
		return new ResBo<List<Category>>(categoryMapper.selectByExample(e));
	}

}
