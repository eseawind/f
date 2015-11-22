package com.f.services.merchant;

import java.util.List;
import java.util.Map;

import com.f.dto.users.Merchant;

import framework.web.Pager;

public interface IMerchant {

	/**
	 * <p>商家查询</p>
	 * @param id 商家编号 name 商家名称  createtime 注册时间
	 * 
	 * */
	public Pager<List<Map<String,Object>>> selectMerchants(Long id,String name,int page, int rows);
	
	public void insertOrUpdateMerchant(Merchant merchant);
	
	public List<Map<String,Object>> selectMerchantsCombobox(String q);
}
