package com.f.services.users.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.commons.User;
import com.f.dao.ext.users.UsersMapperExt;
import com.f.dao.users.HUsersMapper;
import com.f.dao.users.MerchantMapper;
import com.f.dao.users.UAddressMapper;
import com.f.dao.users.UsersMapper;
import com.f.dto.users.BalanceLog;
import com.f.dto.users.HUsers;
import com.f.dto.users.HUsersExample;
import com.f.dto.users.Merchant;
import com.f.dto.users.MerchantExample;
import com.f.dto.users.UAddress;
import com.f.dto.users.UAddressExample;
import com.f.dto.users.Users;
import com.f.services.users.IUsers;

import framework.exception.BusinessException;
import framework.web.ResBo;

@Service
public class UsersSer implements IUsers{
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private HUsersMapper huMapper;
	
	@Autowired
	private UsersMapper uMapper;
	
	@Autowired
	private UsersMapperExt uext;
	
	@Autowired
	private UAddressMapper uaMapper;

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
	public ResBo<Users> insertUser(String username, String password) {
		Users users = new Users();
		if(Pattern.matches("^1[3|4|5|6|7|8|9]\\d{9}$", username)){
			if(uext.isExistMobile(username)){
				 return new ResBo<Users>(114L);
			}
			String mobile = username;
			username = "f" + username;
			for(int i=0;i<10;i++){
				if(uext.isExistUsername(username)){
					username = username + i;
					continue;
				}
				break;
			}
			users.setUsername(username);
			users.setPassword(password);
			users.setMobile(mobile);
			users.setCreatetime(new Date());
			int i = uMapper.insertSelective(users);
			if(i != 1){
				throw new BusinessException(115L);
			}
			return new ResBo<Users>(uext.selectMUsers(null, mobile, password));
		}else{
			if(uext.isExistUsername(username)){
				return new ResBo<Users>(116L);
			}
			users.setUsername(username);
			users.setPassword(password);
			users.setCreatetime(new Date());
			int i = uMapper.insertSelective(users);
			if(i != 1){
				throw new BusinessException(115L);
			}
			return new ResBo<Users>(uext.selectMUsers(username, null, password));
		}
	}

	@Override
	public Users selectMUsers(String username, String password) {
		if(Pattern.matches("^1[3|4|5|6|7|8|9]\\d{9}$", username)){
			return uext.selectMUsers(null, username, password);
		}
		return uext.selectMUsers(username, null, password);
	}

	@Override
	public Users selectMUsers(long userId) {
		return uMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<UAddress> selectMUsersAddress(long userId) {
		UAddressExample e = new UAddressExample();
		e.createCriteria().andUserIdEqualTo(userId);
		return uaMapper.selectByExample(e);
	}

	@Override
	public void insertOrUpdateMUsersAddress(UAddress uaddress) {
		int i = 0;
		if(uaddress.getIsDef() != null&&uaddress.getIsDef() == 1){
			uext.clearUAddressIsDef(uaddress.getUserId());
		}
		if(uaddress.getId() == null){
			i = uext.insertUAddress(uaddress);
		}else{
			i = uext.updateUAddress(uaddress);
		}
		if(i != 1){
			throw new BusinessException(121L);
		}
	}

	@Override
	public void updateMUsersBalance(long userId, BigDecimal balance, BalanceLog log) {
		int i = uext.updateBalance(userId, balance);
		if(i != 1){
			throw new BusinessException(132L);
		}
		i = uext.insertBalanceLog(log);
		if(i != 1){
			throw new BusinessException(133L);
		}
	}

	@Override
	public Users selectMUsersByMap(String username, String mobile) {
		return uext.selectMUsersByMap(username, mobile);
	}

	@Override
	public void updatePassword(User user, String password) {
		int i = 0;
		switch(user.getUType()){
		case users: i=uext.updateMPassword(user.getId(), password);break;
		case merchant: i=uext.updateBPassword(user.getId(), password);break;
		case husers: i=uext.updateHPassword(user.getId(), password);break;
		}
		if(i != 1){
			throw new BusinessException(139L,user.getId());
		}
	}

	@Override
	public void updatePayPassword(long userId, String password) {
		int i = uext.updateMPayPassword(userId, password);
		if(i != 1){
			throw new BusinessException(140L, userId);
		}
	}

}
