package com.f.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

import framework.web.ResBo;
import framework.web.auth.Channel;

@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private IGoods goodsSer;

	@Channel(Constants.B)
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<List<Long>> addGoods(@ModelAttribute Goods goods,@ModelAttribute CGoods cg,@ModelAttribute GCategory gc,@ModelAttribute GStock gs){
		goodsSer.insertGoodsInfo(goods, cg, gc, gs);
		List<Long> list = new ArrayList<Long>();
		list.add(goods.getId());
		list.add(gc.getId());
		list.add(cg.getId());
		return new ResBo<List<Long>>(list);
	}
	@Channel(Constants.B)
	@RequestMapping("updGoods.htm")
	@ResponseBody
	public ResBo<?> updGoods(@ModelAttribute Goods goods){
		goodsSer.updateGoodsInfo(goods, null, null, null);
		return new ResBo<Object>();
	}
	@Channel(Constants.B)
	@RequestMapping("updCGandGS.htm")
	@ResponseBody
	public ResBo<?> updCG(@ModelAttribute CGoods cg,@ModelAttribute GStock gs){
		cg.setId(gs.getCgid());
		goodsSer.updateGoodsInfo(null, cg, null, gs);
		return new ResBo<Object>();
	}
	@Channel(Constants.B)
	@RequestMapping("updGC.htm")
	@ResponseBody
	public ResBo<?> updGC(@ModelAttribute GCategory gc){
		goodsSer.updateGoodsInfo(null, null, gc, null);
		return new ResBo<Object>();
	}
	
}
