package com.f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

	@RequestMapping("{p1}/{p2}.htm")
	public String page(Model model,@PathVariable("p1")String p1,@PathVariable("p2")String p2){
		return p1+"/"+"p2";
	}
	
}
