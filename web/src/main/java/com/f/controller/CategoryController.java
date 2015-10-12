package com.f.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.dto.goods.Category;
import com.f.services.ICategory;

import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategory categorySer;

	@RequestMapping("/list.htm")
	@ResponseBody
	public ResBo<List<Category>> selCategory(@RequestParam("fid") int fid){
		return categorySer.selectCategories(new ReqBo().setParam("fid",fid));
	}
	
	@RequestMapping("/addOrUpd.htm")
	@ResponseBody
	public ResBo<?> addOrUpd(@ModelAttribute Category category){
		return null;
	}
}
