package com.f.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Combobox;
import com.f.commons.Constants;
import com.f.dto.goods.Category;
import com.f.services.ICategory;

import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;

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
	
	@Channel(Constants.H)
	@RequestMapping("/addOrUpd.htm")
	@ResponseBody
	public ResBo<?> addOrUpd(@ModelAttribute Category category){
		return categorySer.insertOrUpdateCategroy(category);
	}
	
	@RequestMapping("/combobox.htm")
	@ResponseBody
	public ResBo<?> selCategoryCombobox(@RequestParam("fid") int fid){
		ResBo<List<Category>> resBo = categorySer.selectCategories(new ReqBo().setParam("fid",fid));
		List<Combobox> list = new ArrayList<Combobox>();
		if(resBo.isSuccess()&&resBo.getResult().size() > 0){
			Combobox com = null;
			for(Category category:resBo.getResult()){
				com = new Combobox();
				com.setId(category.getId());
				com.setK(category.getName());
				com.setV(category.getCode());
				list.add(com);
			}
		}
		return new ResBo<List<Combobox>>(list);
	}
}
