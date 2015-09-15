package com.f.services.users;

import com.f.commons.UType;
import com.f.dto.ext.users.Users;

public interface IUsers {
	
	/**
	 * 根据用户名密码得到用户信息
	 * */
	public Users selUsers(String name,String password,UType type);
	
	/**
	 * 根据用户id得到用户信息
	 * */
	public Users selUsersByID(long id,UType type);
	
	/**
	 * 新增用户信息
	 * */
	public void insertUsers(Users users);
	
	/**
	 * 修改用户信息
	 * */
	public void updateUsers(Users users);

}
