package com.f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.UType;
import com.f.commons.User;
import com.f.dto.users.HUsers;
import com.f.dto.users.Merchant;
import com.f.dto.users.Users;
import com.f.services.users.IUsers;

import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;
import framework.web.session.ISession;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private ISession session;
	@Autowired
	private IUsers userSer;

	@IsLogin(false)
	@RequestMapping("blogin.htm")
	@ResponseBody
	public ResBo<?> blogin(@RequestParam("username")String username,@RequestParam("password")String password){
		Merchant merchant = userSer.selectMerchantUser(username, password);
		if(merchant == null){
			return new ResBo<Object>(109L);
		}
		User user = new User(merchant.getId(),UType.merchant);
		session.replace(Constants.USERINFO, user);
		return new ResBo<Object>();
	}
	
	@IsLogin(false)
	@RequestMapping("hlogin.htm")
	@ResponseBody
	public ResBo<?> hlogin(@RequestParam("username")String username,@RequestParam("password")String password){
		HUsers hu = userSer.selectHUser(username, password);
		if(hu == null){
			return new ResBo<Object>(109L);
		}
		User user = new User(hu.getId(),UType.husers);
		session.replace(Constants.USERINFO, user);
		return new ResBo<Object>();
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("mlogin.htm")
	@ResponseBody
	public ResBo<Boolean> mlogin(@RequestParam("username")String username,@RequestParam("password")String password){
		Users users = userSer.selectMUsers(username, password);
		if(users == null){
			return new ResBo<Boolean>(109L);
		}
		User user = new User(users.getId(),UType.users);
		session.replace(Constants.USERINFO, user);
		return new ResBo<Boolean>(true);
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("mIslogin.htm")
	@ResponseBody
	public ResBo<Boolean> mislogin(){
		Object user = session.get(Constants.USERINFO);
		if(user == null){
			return new ResBo<Boolean>(false);
		}
		return new ResBo<Boolean>(true);
	}
	
	@Channel(Constants.M)
	@RequestMapping("munlogin.htm")
	@ResponseBody
	public ResBo<Boolean> munlogin(){
		session.flush();
		return new ResBo<Boolean>(true);
	}
}
