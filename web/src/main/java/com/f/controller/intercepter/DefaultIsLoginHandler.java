package com.f.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.commons.Constants;

import framework.web.auth.IsLoginHandler;
import framework.web.session.ISession;

public class DefaultIsLoginHandler implements IsLoginHandler{
	
	@Autowired
	private ISession session;

	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			boolean value) {
		Object obj = session.get(Constants.USERINFO);
		return false;
	}

}
