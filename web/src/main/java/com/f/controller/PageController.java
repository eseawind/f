package com.f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import framework.web.auth.IsLogin;

@Controller
@RequestMapping("page")
public class PageController {

	@IsLogin(false)
	@RequestMapping("{p1}/{p2}.htm")
	public String page(Model model,@PathVariable("p1")String p1,@PathVariable("p2")String p2){
		model.addAttribute("staUrl", "http://app.365020.com");
		return p1+"/"+p2;
	}
	
}
