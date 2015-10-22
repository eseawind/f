package com.f.services.users.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.dao.users.HUsersMapper;
import com.f.dao.users.MerchantMapper;
import com.f.dao.users.UsersMapper;
import com.f.dto.users.HUsers;
import com.f.dto.users.HUsersExample;
import com.f.dto.users.Merchant;
import com.f.dto.users.MerchantExample;
import com.f.dto.users.Users;
import com.f.services.users.IUsers;

import framework.exception.BusinessException;

@Service
public class UsersSer implements IUsers{
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private HUsersMapper huMapper;
	
	@Autowired
	private UsersMapper uMapper;

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

	@Override
	public HUsers selectHUser(String name, String password) {
		HUsersExample e = new HUsersExample();
		e.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
		List<HUsers> list = huMapper.selectByExample(e);
		if(list.size() == 0){
			return null;
		}else if(list.size() > 1){
			throw new BusinessException(108L);
		}
		return list.get(0);
	}

	@Override
	public Users insertUser(String username, String password) {
		Users users = new Users();
		
		return null;
	}

	@Override
	public Users selectMUsers(String username, String password) {
		return null;
	}
	
	

}
