package com.f.services.merchant.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.commons.MD5;
import com.f.dao.ext.users.MerchantMapperExt;
import com.f.dao.users.MerchantMapper;
import com.f.dto.users.Merchant;
import com.f.services.merchant.IMerchant;

import framework.web.Pager;

@Service
public class MerchantSer implements IMerchant{
	
	@Autowired
	private MerchantMapperExt merchantExt;
	@Autowired
	private MerchantMapper merchantMapper;

	@Override
	public Pager<List<Map<String, Object>>> selectMerchants(Long id,
			String name, int page,int rows) {
		List<Map<String, Object>> list = merchantExt.selMerchants(id, name, (page-1)*rows, rows);
		long count = merchantExt.countMerchants(id, name);
		return new Pager<List<Map<String,Object>>>(list,count);
	}

	@Override
	public void insertOrUpdateMerchant(Merchant merchant) {
		if(merchant.getPassword() != null){
			merchant.setPassword(MD5.md5Str(merchant.getPassword()));
		}
		if(merchant.getId() == null){
			merchant.setCreatetime(new Date());
			merchantMapper.insertSelective(merchant);
		}else{
			merchant.setUsername(null);
			merchantMapper.updateByPrimaryKeySelective(merchant);
		}
	}

}
