package com.f.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.cart.Buyer;
import com.f.cart.Cart;
import com.f.cart.CartStr;
import com.f.cart.Carts;
import com.f.cart.Settlements;
import com.f.commons.Constants;
import com.f.commons.User;
import com.f.services.ICarts;
import com.f.services.settle.ISettle;

import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;
import framework.web.session.ISession;

/**
 * @author fengmingming
 * */
@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private ISession session;
	
	@Autowired
	private ICarts cartsSer;
	
	@Autowired
	private ISettle settleSer;

	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Integer> addCart(@RequestParam("merchantId")long merchantId,@RequestParam("cgids")String cgids,@RequestParam("number")int number){
		Cart cart = new Cart(merchantId, number, cgids.trim().split(","));
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
			carts.addCart(cart);
			session.replace(Constants.CARTINFO, carts);
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
			carts.addCart(cart);
			cs.setCartStr(carts.toString());
			cartsSer.saveCartStr(cs);
		}
		return new ResBo<Integer>((Integer)carts.getCartsSize());
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("upd.htm")
	@ResponseBody
	public ResBo<Integer> updCart(@RequestParam("cartStr")String cartStr,@RequestParam("number")int number){
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
			carts.updCart(cartStr,number);
			session.replace(Constants.CARTINFO, carts);
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
			carts.updCart(cartStr,number);
			cs.setCartStr(carts.toString());
			cartsSer.saveCartStr(cs);
		}
		return new ResBo<Integer>((Integer)carts.getCartsSize());
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("delete.htm")
	@ResponseBody
	public ResBo<Integer> deleleCart(@RequestParam("cartStr")String cartStr){
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
			carts.deleteCart(cartStr);
			session.replace(Constants.CARTINFO, carts);
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
			carts.deleteCart(cartStr);
			cs.setCartStr(carts.toString());
			cartsSer.saveCartStr(cs);
		}
		return new ResBo<Integer>((Integer)carts.getCartsSize());
	}
	
	@Channel(Constants.M)
	@RequestMapping("size.htm")
	@ResponseBody
	public ResBo<Integer> sizeCart(){
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
		}
		return new ResBo<Integer>((Integer)carts.getCartsSize());
	}
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("check.htm")
	@ResponseBody
	public ResBo<Integer> checkCart(@RequestParam("cartStrs")String cartStrs,@RequestParam("checked") boolean checked){
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
			for(String cartStr:cartStrs.split(",")){
				carts.checkCart(cartStr, checked);
			}
			session.replace(Constants.CARTINFO, carts);
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
			for(String cartStr:cartStrs.split(",")){
				carts.checkCart(cartStr, checked);
			}
			cs.setCartStr(carts.toString());
			cartsSer.saveCartStr(cs);
		}
		return new ResBo<Integer>((Integer)carts.getCartsSize());
	}  
	
	@IsLogin(false)
	@Channel(Constants.M)
	@RequestMapping("settle.htm")
	@ResponseBody
	public ResBo<Settlements> settle(@ModelAttribute Buyer buyer){
		Object uObj  = session.get(Constants.USERINFO);
		Carts carts = null;
		if(uObj == null){
			Object cartsObj = session.get(Constants.CARTINFO);
			if(cartsObj == null){
				carts = new Carts();
			}else{
				carts = (Carts) cartsObj;
			}
		}else{
			User user = (User)uObj;
			CartStr cs = cartsSer.selectCartStr(user.getId());
			carts = new Carts(cs.getCartStr());
			buyer.setUserId(user.getId());
		}
		Settlements settlements = new Settlements();
		if(carts.getCartsSize() > 0){
			Map<Long,Carts> cartsMap= carts.groupByMerchant();
			for(Long merchantId:cartsMap.keySet()){
				buyer.setCurMerchantId(merchantId);
				settlements.getSettlements().add(settleSer.selectSettlement(buyer, cartsMap.get(merchantId)));
			}
			settlements.builder();
		}else{
			settlements.setSettle(false);
		}
		return new ResBo<Settlements>(settlements); 
	}
	
}
