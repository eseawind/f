package com.f.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.User;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.session.ISession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	private ISession session;
	
	@Autowired
	private IGoods goodsSer;

	@Channel(Constants.B)
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<List<Long>> addGoods(@ModelAttribute Goods goods,@ModelAttribute CGoods cg,@ModelAttribute GStock gs){
		User user = (User) session.get(Constants.USERINFO);
		goods.setMerchantId(user.getId());
		goodsSer.insertGoodsInfo(goods, cg, gs);
		List<Long> list = new ArrayList<Long>();
		list.add(goods.getId());
		list.add(cg.getId());
		list.add(gs.getId());
		return new ResBo<List<Long>>(list);
	}
	@Channel(Constants.B)
	@RequestMapping("updGoods.htm")
	@ResponseBody
	public ResBo<?> updGoods(@ModelAttribute Goods goods){
		goodsSer.updateGoodsInfo(goods, null, null);
		return new ResBo<Object>();
	}
	@Channel(Constants.B)
	@RequestMapping("updCGandGS.htm")
	@ResponseBody
	public ResBo<?> updCG(@ModelAttribute CGoods cg,@ModelAttribute GStock gs){
		cg.setId(gs.getCgid());
		gs.setCgid(null);
		goodsSer.updateGoodsInfo(null, cg, gs);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("addCG.htm")
	@ResponseBody
	public ResBo<?> addCG(@ModelAttribute CGoods cg,@ModelAttribute GStock gs){
		goodsSer.insertCGoodsInfo(cg, gs);
		List<Long> list = new ArrayList<Long>();
		list.add(cg.getId());
		list.add(gs.getId());
		return new ResBo<List<Long>>(list);
	}
	
	@Channel(Constants.B)
	@RequestMapping("blist.htm")
	@ResponseBody
	public ResBo<?> list(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Pager<List<Map<String,Object>>>>(goodsSer.selectCGoods(reqBo.getParamLong("id"), reqBo.getParamStr("sku"), reqBo.getParamStr("gname"), user.getId(), reqBo.getParamLong("brandId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
}
