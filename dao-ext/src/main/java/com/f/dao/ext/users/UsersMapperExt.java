package com.f.dao.ext.users;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.f.dto.users.BalanceLog;
import com.f.dto.users.UAddress;
import com.f.dto.users.Users;

public interface UsersMapperExt {
	
	public boolean isExistMobile(String mobile);
	
	public boolean isExistUsername(String username);
	
	public int insertUsers(Users users);
	
	public Users selectMUsers(@Param("username")String username,@Param("mobile")String mobile,@Param("password")String password);
	
	public Users selectMUsersByMap(@Param("username")String username,@Param("mobile")String mobile);
	
	public int insertUAddress(UAddress ua);
	
	public int updateUAddress(UAddress ua);
	
	public int clearUAddressIsDef(long userId);
	
	public int updateBalance(@Param("userId")long userId,@Param("balance")BigDecimal balance);
	
	public int insertBalanceLog(BalanceLog log);
	
	public int updateMPassword(@Param("userId")long userId,@Param("password")String password);
	
	public int updateBPassword(@Param("userId")long userId,@Param("password")String password);
	
	public int updateHPassword(@Param("userId")long userId,@Param("password")String password);
	
	public int updateMPayPassword(@Param("userId")long userId,@Param("password")String password);
	
}
