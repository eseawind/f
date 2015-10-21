package com.f.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Combobox;
import com.f.commons.Constants;
import com.f.commons.EFilter;
import com.f.commons.ESort;
import com.f.commons.GoodsStaInfo;
import com.f.dto.goods.Category;
import com.f.services.ICategory;
import com.f.services.goods.IGoods;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private IGoods goodsSer;
	
	@Autowired
	private ICategory categorySer;

	@IsLogin(false)
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
	
	@IsLogin(false)
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
	
	@Channel(Constants.M)
	@IsLogin(false)
	@RequestMapping("/mlist/{code}.htm")
	public String mlist(@PathVariable("code")String code,@RequestParam(value="merchantId",required=false)Long merchantId,Model model){
		model.addAttribute("code", code);
		model.addAttribute("merchantId", merchantId);
		return "list/mglist";
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("/mlist.htm")
	@ResponseBody
	public ResBo<Pager<List<GoodsStaInfo>>> selGoodsStaInfo(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		EFilter filter = EFilter.getEFilter(reqBo.getParamInt("filter",3));
		ESort sort = ESort.getESort(reqBo.getParamInt("sort",0));
		return new ResBo<Pager<List<GoodsStaInfo>>>(goodsSer.selectCGoodsStaInfo(reqBo.getParamLong("merchantId"), reqBo.getParamStr("code"), filter, sort, reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}
}
