package com.f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.UType;
import com.f.commons.User;
import com.f.dto.users.Users;
import com.f.services.users.IUsers;

import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;
import framework.web.session.ISession;

@Controller
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private ISession session;
	@Autowired
	private IUsers usersSer;
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("mregister.htm")
	@ResponseBody
	public ResBo<Boolean> register(@RequestParam("username")String username,@RequestParam("password")String password){
		ResBo<Users> resBo = usersSer.insertUser(username, password);
		if(!resBo.isSuccess()){
			return new ResBo<Boolean>(resBo.getErrCode());
		}
		Users users = resBo.getResult();
		User user = new User(users.getId(),UType.users);
		session.replace(Constants.USERINFO, user);
		return new ResBo<Boolean>(true);
	}
	
}
