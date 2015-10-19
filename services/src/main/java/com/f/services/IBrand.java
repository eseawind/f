package com.f.services;

import java.util.List;

import com.f.commons.Combobox;
import com.f.dto.goods.Brand;

import framework.web.Pager;

public interface IBrand {

	public Pager<List<Brand>> selectBrands(Long merchant,String name);
	
	public List<Combobox> selectCombobox(Long merchant);
	
	public void insertBrand(Brand brand);
	
	public void updateBrand(Brand brand);
}
