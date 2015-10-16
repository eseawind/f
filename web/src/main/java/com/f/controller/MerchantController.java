package com.f.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.commons.Constants;
import com.f.dto.users.Merchant;
import com.f.services.merchant.IMerchant;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;

@Controller
@RequestMapping("merchant")
public class MerchantController {
	@Autowired
	private IMerchant merchantSer;

	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String, Object>>>> selMerchants(
			HttpServletRequest req) {
		ReqBo reqBo = new ReqBo(req);
		return new ResBo<Pager<List<Map<String, Object>>>>(
				merchantSer.selectMerchants(reqBo.getParamLong("id"),
						reqBo.getParamStr("name"), reqBo.getParamInt("page"),
						reqBo.getParamInt("rows")));
	}
	
	@Channel(Constants.H)
	@RequestMapping("addOrUpd.htm")
	@ResponseBody
	public ResBo<?> addOrUpdMerchant(@ModelAttribute Merchant merchant) {
		merchantSer.insertOrUpdateMerchant(merchant);
		return new ResBo<Object>();
	}
}
