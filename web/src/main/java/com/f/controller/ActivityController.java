package com.f.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.commons.User;
import com.f.dao.DictMapper;
import com.f.dto.Dict;
import com.f.dto.DictExample;
import com.f.dto.goods.Dcgoods;
import com.f.dto.goods.Dynpage;
import com.f.services.goods.IDynpage;

import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;
import framework.web.session.ISession;

@Controller
@RequestMapping
public class ActivityController {
	@Autowired
	private IDynpage dynSer;
	@Autowired
	private ISession session;
	@Autowired
	private DictMapper dictMapper;

	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("dynpage/cgids.htm")
	@ResponseBody
	public ResBo<?> dynpageCgids(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		int rows = reqBo.getParamInt("rows", 50);
		int page = reqBo.getParamInt("page",1);
		return new ResBo<Object>(dynSer.selectDcgoods(null, reqBo.getParamLong("pageId"), reqBo.getParamInt("type"), page, rows));
	}
	
	@Channel(Constants.B)
	@RequestMapping("dynpage/list.htm")
	@ResponseBody
	public ResBo<?> dynpage_list(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		int rows = reqBo.getParamInt("rows", 50);
		int page = reqBo.getParamInt("page",1);
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Object>(dynSer.selectDynpage(user.getId(), reqBo.getParamInt("type"), page, rows));
	}
	
	@Channel(Constants.B)
	@RequestMapping("dcgoods/list.htm")
	@ResponseBody
	public ResBo<?> dcgoods_list(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		int rows = reqBo.getParamInt("rows", 50);
		int page = reqBo.getParamInt("page",1);
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Object>(dynSer.selectDcgoods(user.getId(),reqBo.getParamLong("pageId"), reqBo.getParamInt("type"), page, rows));
	}
	
	@Channel(Constants.B)
	@RequestMapping("dynpage/add.htm")
	@ResponseBody
	public ResBo<?> add(@ModelAttribute Dynpage page,@RequestParam("cgids")String cgids){
		User user = (User) session.get(Constants.USERINFO);
		page.setMerchantId(user.getId());
		List<Dcgoods> list = new ArrayList<Dcgoods>();
		for(String cgid:cgids.split(",")){
			Dcgoods dcg = new Dcgoods();
			dcg.setCgid(Long.valueOf(cgid));
			list.add(dcg);
		}
		dynSer.insertDynpage(page, list);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("dynpage/upd.htm")
	@ResponseBody
	public ResBo<?> upd(@ModelAttribute Dynpage page){
		dynSer.updateDynpage(page);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("dynpage/dcgoods/upd.htm")
	@ResponseBody
	public ResBo<?> upd_dcgoods(@ModelAttribute Dcgoods dcg){
		dynSer.updateDcgoods(dcg);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.H)
	@RequestMapping("dict/tree.htm")
	@ResponseBody
	public ResBo<?> dict_tree(@RequestParam("fid")long fid){
		DictExample e = new DictExample();
		e.createCriteria().andFidEqualTo(fid);
		return new ResBo<Object>(dictMapper.selectByExample(e));
	}
	
	@Channel(Constants.B)
	@RequestMapping("dict/combobox.htm")
	@ResponseBody
	public ResBo<?> dict_combobox(@RequestParam("fid")long fid){
		DictExample e = new DictExample();
		e.createCriteria().andFidEqualTo(fid).andIsDelEqualTo(1);
		return new ResBo<Object>(dictMapper.selectByExample(e));
	}
	
	@Channel(Constants.H)
	@RequestMapping("dict/addOrUpd.htm")
	@ResponseBody
	public ResBo<?> dict_addOrUpd(@ModelAttribute Dict dict){
		if(dict.getId() == null){
			dictMapper.insertSelective(dict);
		}else{
			dictMapper.updateByPrimaryKeySelective(dict);
		}
		return new ResBo<Object>();
	}
	
}
