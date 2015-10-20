package com.f.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.EFilter;
import com.f.commons.ESort;
import com.f.commons.GoodsDynInfo;
import com.f.commons.GoodsStaInfo;
import com.f.commons.User;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.Goods;
import com.f.services.goods.IGoods;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;
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
	public ResBo<List<Long>> addGoods(@ModelAttribute Goods goods,@ModelAttribute CGoods cg){
		User user = (User) session.get(Constants.USERINFO);
		goods.setMerchantId(user.getId());
		goodsSer.insertGoodsInfo(goods, cg);
		List<Long> list = new ArrayList<Long>();
		list.add(goods.getId());
		list.add(cg.getId());
		return new ResBo<List<Long>>(list);
	}
	@Channel(Constants.B)
	@RequestMapping("updGoods.htm")
	@ResponseBody
	public ResBo<?> updGoods(@ModelAttribute Goods goods){
		goodsSer.updateGoodsInfo(goods, null);
		return new ResBo<Object>();
	}
	@Channel(Constants.B)
	@RequestMapping("updCGandGS.htm")
	@ResponseBody
	public ResBo<?> updCG(@ModelAttribute CGoods cg){
		goodsSer.updateGoodsInfo(null, cg);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("addCG.htm")
	@ResponseBody
	public ResBo<?> addCG(@ModelAttribute CGoods cg){
		goodsSer.insertCGoodsInfo(cg);
		return new ResBo<Long>(cg.getId());
	}
	
	@Channel(Constants.B)
	@RequestMapping("blist.htm")
	@ResponseBody
	public ResBo<?> list(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Pager<List<Map<String,Object>>>>(goodsSer.selectCGoods(reqBo.getParamLong("id"), reqBo.getParamStr("sku"), reqBo.getParamStr("gname"), user.getId(), reqBo.getParamLong("brandId"),reqBo.getParamInt("page"),reqBo.getParamInt("rows")));
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("mlist.htm")
	@ResponseBody
	public ResBo<Pager<List<GoodsStaInfo>>> selGoodsStaInfo(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		EFilter filter = EFilter.getEFilter(reqBo.getParamInt("filter",0));
		ESort sort = ESort.getESort(reqBo.getParamInt("sort",0));
		return new ResBo<Pager<List<GoodsStaInfo>>>(goodsSer.selectCGoodsStaInfo(reqBo.getParamLong("merchantId"), reqBo.getParamStr("code"), filter, sort, reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("/sta/cgids.htm")
	@ResponseBody
	public ResBo<List<GoodsStaInfo>> selGoodsStaInfoByCgIds(@RequestParam("cgids")String cgids){
		String[] cgidA = cgids.split(",");
		List<Long> list = new ArrayList<Long>(cgidA.length);
		for(String cgid:cgidA){
			list.add(Long.parseLong(cgid));
		}
		ResBo<List<GoodsStaInfo>> resBo = new ResBo<List<GoodsStaInfo>>();
		if(list.size() > 0){
			resBo.setResult(goodsSer.selectCGoodsStaInfoByCgIds(list));
		}
		return resBo;
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("/detail/{cgid}.htm")
	public String selGoodsDetail(@PathVariable("cgid")long cgid,Model model){
		List<GoodsStaInfo> list = goodsSer.selectCGoodsStaInfoByCgId(cgid);
		GoodsStaInfo def = null;
		List<Long> cgids = new ArrayList<Long>();
		for(GoodsStaInfo info:list){
			if(info.getCgid().equals(cgid)){
				def = info;
				cgids.add(info.getCgid());
			}
		}
		List<GoodsDynInfo> list2 = goodsSer.selectCGoodsDynInfo(cgids);
		model.addAttribute("def", def);
		model.addAttribute("cgs", list);
		model.addAttribute("dyn", list2.get(0));
		return "goods/mdetail";
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("/dyn/cgids.htm")
	@ResponseBody
	public ResBo<List<GoodsDynInfo>> selGoodsDynInfoByCgIds(@RequestParam("cgids")String cgids){
		String[] cgidA = cgids.split(",");
		List<Long> list = new ArrayList<Long>(cgidA.length);
		for(String cgid:cgidA){
			list.add(Long.parseLong(cgid));
		}
		ResBo<List<GoodsDynInfo>> resBo = new ResBo<List<GoodsDynInfo>>();
		if(list.size() > 0){
			resBo.setResult(goodsSer.selectCGoodsDynInfo(list));
		}
		return resBo;
	}
}
