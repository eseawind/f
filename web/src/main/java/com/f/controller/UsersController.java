package com.f.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.UType;
import com.f.commons.User;
import com.f.dto.users.UAddress;
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
	
	
	@Channel(Constants.M)
	@RequestMapping("minfo.htm")
	@ResponseBody
	public ResBo<Users> minfo(){
		User user = (User) session.get(Constants.USERINFO);
		Users users = usersSer.selectMUsers(user.getId());
		users.setPassword(null);
		return new ResBo<Users>(users);
	}
	
	@Channel(Constants.M)
	@RequestMapping("maddress.htm")
	@ResponseBody
	public ResBo<List<UAddress>> maddress(){
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<List<UAddress>>(usersSer.selectMUsersAddress(user.getId()));
	}
	
	@Channel(Constants.M)
	@RequestMapping("maddOrUpdAddress.htm")
	@ResponseBody
	public ResBo<?> maddOrUpdAddress(@ModelAttribute UAddress ua){
		User user = (User) session.get(Constants.USERINFO);
		ua.setUserId(user.getId());
		if(ua.getId() == null){
			if(usersSer.selectMUsersAddress(user.getId()).size() >= 5){
				return new ResBo<Object>(5L);
			}
		}
		usersSer.insertOrUpdateMUsersAddress(ua);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.M)
	@RequestMapping("updpass.htm")
	@ResponseBody
	public ResBo<?> updpass(@RequestParam("password")String password,@RequestParam("oldpass")String oldpass){
		User user = (User) session.get(Constants.USERINFO);
		Users users = usersSer.selectMUsers(user.getId());
		if(!users.getPassword().equals(oldpass)){
			return new ResBo<Object>(141);
		}
		usersSer.updatePassword(user, password);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.M)
	@RequestMapping("updppass.htm")
	@ResponseBody
	public ResBo<?> updppass(@RequestParam("password")String password,@RequestParam("verifycode")String verifycode){
		User user = (User) session.get(Constants.USERINFO);
		usersSer.updatePayPassword(user.getId(), password);
		return new ResBo<Object>();
	}
}
