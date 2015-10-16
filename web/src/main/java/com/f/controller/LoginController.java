package com.f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.f.commons.Constants;
import com.f.dto.users.Merchant;
import com.f.services.users.IUsers;

import framework.web.ResBo;
import framework.web.auth.Channel;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private IUsers userSer;

	@Channel(Constants.B)
	@RequestMapping("blogin.htm")
	public ResBo<?> blogin(@RequestParam("username")String username,@RequestParam("password")String password){
		Merchant merchant = userSer.selectMerchantUser(username, password);
		if(merchant == null){
			return new ResBo<Object>(109L);
		}
		return new ResBo<Object>();
	}
}
