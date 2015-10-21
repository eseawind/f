package com.f.services.users;

import com.f.dto.users.HUsers;
import com.f.dto.users.Merchant;

public interface IUsers {
	
	/**
	 * 根据用户名密码得到用户信息
	 * */
	public Merchant selectMerchantUser(String name,String password);
	
	public HUsers selectHUser(String name,String password);

}
