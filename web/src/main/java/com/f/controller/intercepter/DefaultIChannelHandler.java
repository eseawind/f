package com.f.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.commons.Constants;
import com.f.commons.UType;
import com.f.commons.User;

import framework.exception.BusinessException;
import framework.web.auth.IChannelHandler;
import framework.web.session.ISession;

public class DefaultIChannelHandler implements IChannelHandler{
	
	@Autowired
	private ISession session;

	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			String[] values) {
		if(values.length == 0)return true;
		Object uObj = session.get(Constants.USERINFO);
		if(uObj instanceof User){
			User user = (User) uObj;
			UType utype = user.getUType();
			switch(utype){
			case users: return isExist(values,Constants.M);
			case merchant: return isExist(values,Constants.B);
			case husers: return isExist(values,Constants.H);
			default:throw new BusinessException(9L);
			}
		}
		return true;
	}
	
	private boolean isExist(String[] values,String value){
		for(String str:values){
			if(value.equals(str)){
				return true;
			}
		}
		throw new BusinessException(8L);
	}

}
