package com.f.controller.intercepter;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.f.commons.Constants;
import com.f.util.JsonUtil;

import framework.exception.ApplicationException;
import framework.web.ResBo;
import framework.web.auth.IsLoginHandler;
import framework.web.session.ISession;

public class DefaultIsLoginHandler implements IsLoginHandler{
	
	@Autowired
	private ISession session;

	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			boolean value) {
		Object obj = session.get(Constants.USERINFO);
		if(obj == null&&value){
			ResBo<?> resBo = new ResBo<Object>(7L);
			try {
				res.getWriter().write(JsonUtil.marshal(resBo));
				res.getWriter().flush();
			} catch (IOException e) {
				throw new ApplicationException("res.getWriter", e);
			}
			return false;
		}
		return true;
	}

}
