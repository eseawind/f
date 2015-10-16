package com.f.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.web.auth.IChannelHandler;

public class DefaultIChannelHandler implements IChannelHandler{

	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			String[] values) {
		return false;
	}

}
