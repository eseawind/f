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
import com.f.commons.GoodsDynInfo;
import com.f.commons.GoodsStaInfo;
import com.f.commons.UType;
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
		User user = (User) session.get(Constants.USERINFO);
		goods.setMerchantId(user.getId());
		goodsSer.updateGoodsInfo(user.getId(), goods, null);
		return new ResBo<Object>();
	}
	@Channel(Constants.B)
	@RequestMapping("updCGandGS.htm")
	@ResponseBody
	public ResBo<?> updCG(@ModelAttribute CGoods cg){
		User user = (User) session.get(Constants.USERINFO);
		goodsSer.updateGoodsInfo(user.getId(), null, cg);
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
	@RequestMapping("/detail/{cgid}.htm")
	public String selGoodsDetail(@PathVariable("cgid")long cgid,Model model){
		List<GoodsStaInfo> list = goodsSer.selectCGoodsStaInfoByCgId(cgid);
		GoodsStaInfo def = null;
		for(GoodsStaInfo info:list){
			if(info.getCgid().equals(cgid)){
				def = info;
			}
		}
		model.addAttribute("def", def);
		model.addAttribute("cgs", list);
		Object obj = session.get(Constants.USERINFO);
		if(obj != null){
			User user = (User) obj;
			if(user.getUType() == UType.users){
				model.addAttribute("isCollect", goodsSer.selectIsCollect(user.getId(), cgid));
			}
		}
		return "goods/mdetail";
	}
	
	@IsLogin(false)
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
	
	@Channel(Constants.M)
	@RequestMapping("/collect.htm")
	@ResponseBody
	public ResBo<?> collect(@RequestParam("cgid")long cgid){
		User user = (User) session.get(Constants.USERINFO);
		if(goodsSer.selectIsCollect(user.getId(), cgid)){
			return new ResBo<Object>(142);
		}else{
			goodsSer.insertCollect(user.getId(), cgid);
		}
		return new ResBo<Object>();
	}
	
	@Channel(Constants.M)
	@RequestMapping("/collect/list.htm")
	@ResponseBody
	public ResBo<?> collects(@RequestParam("page")int page,@RequestParam("rows")int rows){
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Object>(goodsSer.selectCollects(user.getId(), page, rows));
	}
	
	@Channel(Constants.M)
	@RequestMapping("/collect/delete.htm")
	@ResponseBody
	public ResBo<?> delCollect(@RequestParam("cgid")long cgid){
		User user = (User) session.get(Constants.USERINFO);
		goodsSer.deleteCollect(user.getId(), cgid);
		return new ResBo<Object>();
	}
	
}
