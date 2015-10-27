package com.f.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Combobox;
import com.f.commons.Constants;
import com.f.commons.User;
import com.f.dto.goods.Brand;
import com.f.services.IBrand;

import framework.web.Pager;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.session.ISession;

@Controller
@RequestMapping("brand")
public class BrandController {
	@Autowired
	private ISession session;
	@Autowired
	private IBrand brandSer;
	

	@Channel(Constants.B)
	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<Pager<List<Brand>>> list(@RequestParam(value="name",required=false)String name,@RequestParam("page")int page,@RequestParam("rows")int rows){
		User user = (User)session.get(Constants.USERINFO);
		return new ResBo<Pager<List<Brand>>>(brandSer.selectBrands(user.getId(), name, page, rows));
	}
	
	@Channel(Constants.B)
	@RequestMapping("combobox.htm")
	@ResponseBody
	public ResBo<List<Combobox>> combobox(){
		User user = (User)session.get(Constants.USERINFO);
		return new ResBo<List<Combobox>>(brandSer.selectCombobox(user.getId()));
	}
	
	@Channel(Constants.B)
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<?> addBrand(@ModelAttribute Brand brand){
		User user = (User)session.get(Constants.USERINFO);
		brand.setMerchantId(user.getId());
		brandSer.insertBrand(brand);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("upd.htm")
	@ResponseBody
	public ResBo<?> updBrand(@ModelAttribute Brand brand){
		User user = (User)session.get(Constants.USERINFO);
		brand.setMerchantId(user.getId());
		brandSer.updateBrand(brand);
		return new ResBo<Object>();
	}
}
