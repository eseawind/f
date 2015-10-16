package com.f.services.users.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.dao.users.MerchantMapper;
import com.f.dto.users.Merchant;
import com.f.dto.users.MerchantExample;
import com.f.services.users.IUsers;

import framework.exception.BusinessException;

public class UsersSer implements IUsers{
	
	@Autowired
	private MerchantMapper merchantMapper;

	@Override
	public Merchant selectMerchantUser(String name, String password) {
		MerchantExample e = new MerchantExample();
		e.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
		List<Merchant> list = merchantMapper.selectByExample(e);
		if(list.size() == 0){
			return null;
		}else if(list.size() > 1){
			throw new BusinessException(108L);
		}
		return list.get(0);
	}
	
	

}
