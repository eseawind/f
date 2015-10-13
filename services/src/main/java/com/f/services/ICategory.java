package com.f.services;

import java.util.List;

import com.f.dto.goods.Category;

import framework.web.ReqBo;
import framework.web.ResBo;

public interface ICategory {
	
	public ResBo<List<Category>> selectCategories(ReqBo reqBo);
	
	public ResBo<?> insertOrUpdateCategroy(Category category);

}
