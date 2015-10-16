package com.f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.UType;
import com.f.commons.User;
import com.f.dto.users.Merchant;
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

	@Channel(Constants.B)
	@IsLogin(false)
	@RequestMapping("blogin.htm")
	@ResponseBody
	public ResBo<?> blogin(@RequestParam("username")String username,@RequestParam("password")String password){
		Merchant merchant = userSer.selectMerchantUser(username, password);
		if(merchant == null){
			return new ResBo<Object>(109L);
		}
		User user = new User(merchant.getId(),UType.merchant);
		session.set(Constants.USERINFO, user);
		return new ResBo<Object>();
	}
}