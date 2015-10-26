package com.f.dao.ext.users;

import org.apache.ibatis.annotations.Param;

import com.f.dto.users.Users;

public interface UsersMapperExt {
	
	public boolean isExistMobile(String mobile);
	
	public boolean isExistUsername(String username);
	
	public int insertUsers(Users users);
	
	public Users selectMUsers(@Param("username")String username,@Param("mobile")String mobile,@Param("password")String password);
	
}
