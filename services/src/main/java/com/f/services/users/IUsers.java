package com.f.services.users;

import java.util.List;

import com.f.dto.users.HUsers;
import com.f.dto.users.Merchant;
import com.f.dto.users.UAddress;
import com.f.dto.users.Users;

import framework.web.ResBo;

public interface IUsers {
	
	/**
	 * 根据用户名密码得到用户信息
	 * */
	public Merchant selectMerchantUser(String name,String password);
	
	public HUsers selectHUser(String name,String password);
	
	public ResBo<Users> insertUser(String username,String password);
	
	public Users selectMUsers(String username,String password);
	
	public Users selectMUsers(long userId);
	
	public List<UAddress> selectMUsersAddress(long userId);

}
