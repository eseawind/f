package com.f.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.web.auth.IRoleHandler;

public class DefaultIRoleHandler implements IRoleHandler{

	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			boolean value) {
		return true;
	}

}
