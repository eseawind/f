package com.f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;

import framework.web.ResBo;

@Controller
@RequestMapping("goods")
public class GoodsController {

	
	@RequestMapping("addOrUpd.htm")
	@ResponseBody
	public ResBo<Long> addOrUpdGoods(@ModelAttribute Goods goods,@ModelAttribute CGoods cg,@ModelAttribute GCategory gc,@ModelAttribute GStock gs){
		return null;
	}
	
}
