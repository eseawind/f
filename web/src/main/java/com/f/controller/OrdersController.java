package com.f.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.cart.Buyer;
import com.f.cart.CartStr;
import com.f.cart.Carts;
import com.f.cart.Settlement;
import com.f.cart.Settlements;
import com.f.commons.Constants;
import com.f.commons.User;
import com.f.dto.users.Users;
import com.f.orders.ResOrders;
import com.f.services.ICarts;
import com.f.services.orders.IOrders;
import com.f.services.settle.ISettle;
import com.f.services.users.IUsers;

import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.session.ISession;

@Controller
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private ISession session;
	@Autowired
	private ICarts cartsSer;
	@Autowired
	private ISettle settleSer;
	@Autowired
	private IOrders orderSer;
	@Autowired
	private IUsers usersSer;

	@Channel(Constants.M)
	@RequestMapping("commitOrders.htm")
	@ResponseBody
	public ResBo<?> commitOrders(@ModelAttribute Buyer buyer) {
		ResBo<?> valid = buyer.validate();
		if (!valid.isSuccess()) {
			return valid;
		}
		User user = (User) session.get(Constants.USERINFO);
		buyer.setUserId(user.getId());
		buyer.setChannel(Constants.M);
		CartStr cs = cartsSer.selectCartStr(user.getId());
		Carts carts = new Carts(cs.getCartStr());
		Settlements settlements = new Settlements();
		if (carts.getCartsSize() > 0) {
			Map<Long, Carts> cartsMap = carts.groupByMerchant();
			for (Long merchantId : cartsMap.keySet()) {
				buyer.setCurMerchantId(merchantId);
				settlements.getSettlements().add(
						settleSer.selectSettlement(buyer,
								cartsMap.get(merchantId)));
			}
			settlements.builder();
		} else {
			settlements.setSettle(false);
		}
		if (settlements.isSettle()) {
			return new ResBo<ResOrders>(orderSer.insertCommitOrders(buyer,
					settlements));
		}
		StringBuilder sb = new StringBuilder();
		for (Settlement settlement : settlements.getSettlements()) {
			if (!settlement.isSettle()) {
				sb.append(settlement.getReason());
			}
		}
		ResBo<Settlements> resBo = new ResBo<Settlements>(123L, sb.toString());
		resBo.setResult(settlements);
		return resBo;
	}

	@Channel(Constants.M)
	@RequestMapping("success.htm")
	public String success(HttpServletRequest req, Model model) {
		ReqBo reqBo = new ReqBo(req);
		model.addAttribute(reqBo.getParams());
		return "orders/success";
	}

	@Channel(Constants.M)
	@RequestMapping("mdetail.htm")
	@ResponseBody
	public ResBo<Map<String, Object>> detail(
			@RequestParam("orderId") long orderId) {
		User user = (User) session.get(Constants.USERINFO);
		return new ResBo<Map<String, Object>>(orderSer.selectODetail(orderId,
				user.getId(), null));
	}

	@Channel(Constants.M)
	@RequestMapping("mlist.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String, Object>>>> mlist(HttpServletRequest req) {
		ReqBo reqBo = new ReqBo(req);
		User user = (User) session.get(Constants.USERINFO);
		int rows = reqBo.getParamInt("rows");
		if(rows > 10){
			return new ResBo<Pager<List<Map<String,Object>>>>(137L);
		}
		return new ResBo<Pager<List<Map<String, Object>>>>(
				orderSer.selectOrders(user.getId(), null, null,
						reqBo.getParamInt("isPaid"),
						reqBo.getParamInt("state"),
						reqBo.getParamInt("status"),
						reqBo.getParamDate("sdate"),
						reqBo.getParamDate("edate"), reqBo.getParamInt("page"),
						rows));
	}

	@Channel(Constants.B)
	@RequestMapping("blist.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String, Object>>>> blist(HttpServletRequest req) {
		ReqBo reqBo = new ReqBo(req);
		User user = (User) session.get(Constants.USERINFO);
		Long userId = null;
		if(reqBo.getParamStr("mobile") != null){
			Users users = usersSer.selectMUsersByMap(null, reqBo.getParamStr("mobile"));
			if(users == null){
				return new ResBo<Pager<List<Map<String,Object>>>>(136L);
			}
			userId = users.getId();
		}else if(reqBo.getParamStr("username") != null){
			Users users = usersSer.selectMUsersByMap(null, reqBo.getParamStr("username"));
			if(users == null){
				return new ResBo<Pager<List<Map<String,Object>>>>(136L);
			}
			userId = users.getId();
		}
		int rows = reqBo.getParamInt("rows");
		if(rows > 10){
			return new ResBo<Pager<List<Map<String,Object>>>>(137L);
		}
		return new ResBo<Pager<List<Map<String, Object>>>>(
				orderSer.selectOrders(userId, user.getId(),
						reqBo.getParamStr("orderNum"),
						reqBo.getParamInt("isPaid"),
						reqBo.getParamInt("state"),
						reqBo.getParamInt("status"),
						reqBo.getParamDate("sdate"),
						reqBo.getParamDate("edate"), reqBo.getParamInt("page"),
						rows));
	}

}
