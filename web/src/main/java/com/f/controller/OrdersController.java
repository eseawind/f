package com.f.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.f.commons.EIsPaid;
import com.f.commons.EOrdersState;
import com.f.commons.EOrdersStatus;
import com.f.commons.User;
import com.f.dto.users.Users;
import com.f.orders.ResOrders;
import com.f.services.ICarts;
import com.f.services.orders.IOrders;
import com.f.services.settle.ISettle;
import com.f.services.users.IUsers;
import com.f.util.MapUtil;

import framework.exception.BusinessException;
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
	
	@Channel(Constants.M)
	@RequestMapping("cancel.htm")
	@ResponseBody
	public ResBo<?> cancelOrder(@RequestParam("orderId")long orderId){
		User user = (User) session.get(Constants.USERINFO);
		orderSer.updateOrders(orderId, user.getId(), null, EOrdersState.State_2.getCode(), null, null);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.M)
	@RequestMapping("confirmReceipt.htm")
	@ResponseBody
	public ResBo<?> confirmReceipt(@RequestParam("orderId")long orderId){
		User user = (User) session.get(Constants.USERINFO);
		orderSer.updateOrders(orderId, user.getId(), null, null, EOrdersStatus.Status_5.getCode(), null);
		return new ResBo<Object>();
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
	
	@Channel(Constants.B)
	@RequestMapping("boutstock.htm")
	@ResponseBody
	public ResBo<?> boutstock(@RequestParam("orderId")long orderId){
		User user = (User) session.get(Constants.USERINFO);
		orderSer.updateOrders(orderId, null, user.getId(), null, EOrdersStatus.Status_4.getCode(), null);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("boutstocks.htm")
	@ResponseBody
	public ResBo<?> boutstocks(@RequestParam("orderIds")String orderIds){
		User user = (User) session.get(Constants.USERINFO);
		List<Long> orderList = new ArrayList<Long>();
		for(String orderId:orderIds.split(",")){
			orderList.add(Long.valueOf(orderId));
		}
		if(orderList.size() > 0){
			orderSer.updateOrdersBatch(orderList, null, user.getId(), null, EOrdersStatus.Status_4.getCode(), null);
		}
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("bexcel.htm")
	public void bexcel(HttpServletRequest req,HttpServletResponse res) throws Exception{
		ReqBo reqBo = new ReqBo(req);
		Date sdate = reqBo.getParamDate("sdate");
		Date edate = reqBo.getParamDate("edate");
		if(sdate == null||edate == null||edate.getTime() - sdate.getTime() > 100*60*60*24*31){
			res.getWriter().write(BusinessException.getMessage(138L));
			return;
		}
		User user = (User) session.get(Constants.USERINFO);
		List<Map<String, Object>> list = orderSer.selectOrdersExcel(user.getId(), reqBo.getParamInt("isPaid"), reqBo.getParamInt("state"), reqBo.getParamInt("status"), sdate, edate);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);
		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("订单号");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("是否付款");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("订单状态");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("订单处理状态");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("货款金额");
		row.createCell(5, Cell.CELL_TYPE_STRING).setCellValue("订单金额");
		row.createCell(6, Cell.CELL_TYPE_STRING).setCellValue("优惠金额");
		row.createCell(7, Cell.CELL_TYPE_STRING).setCellValue("下单时间");
		row.createCell(8, Cell.CELL_TYPE_STRING).setCellValue("省");
		row.createCell(9, Cell.CELL_TYPE_STRING).setCellValue("市");
		row.createCell(10, Cell.CELL_TYPE_STRING).setCellValue("区");
		row.createCell(11, Cell.CELL_TYPE_STRING).setCellValue("支付方式");
		row.createCell(12, Cell.CELL_TYPE_STRING).setCellValue("sku");
		row.createCell(13, Cell.CELL_TYPE_STRING).setCellValue("商品名称");
		row.createCell(14, Cell.CELL_TYPE_STRING).setCellValue("商品规格");
		row.createCell(15, Cell.CELL_TYPE_STRING).setCellValue("数量");
		row.createCell(16, Cell.CELL_TYPE_STRING).setCellValue("购买金额");
		int i = 1;
		String curOrderNum = null;
		for(Map<String,Object> m:list){
			MapUtil util = new MapUtil(m);
			row = sheet.createRow(i);
			if(!util.getString("orderNum").equals(curOrderNum)){
				curOrderNum = util.getString("orderNum");
				row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(util.getString("orderNum",StringUtils.EMPTY));
				row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(EIsPaid.getEIsPaid(util.getInteger("isPaid")).getName());
				row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(EOrdersState.getEOrdersState(util.getInteger("state")).getName());
				row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(EOrdersStatus.getEOrdersStatus(util.getInteger("status")).getName());
				row.createCell(4, Cell.CELL_TYPE_NUMERIC).setCellValue(util.getBigDecimal("productPrice").doubleValue());
				row.createCell(5, Cell.CELL_TYPE_NUMERIC).setCellValue(util.getBigDecimal("orderPrice").doubleValue());
				row.createCell(6, Cell.CELL_TYPE_NUMERIC).setCellValue(util.getBigDecimal("discountPrice").doubleValue());
				row.createCell(7, Cell.CELL_TYPE_STRING).setCellValue(util.getDateFormat("createtime"));
				row.createCell(8, Cell.CELL_TYPE_STRING).setCellValue(util.getString("provinceName",StringUtils.EMPTY));
				row.createCell(9, Cell.CELL_TYPE_STRING).setCellValue(util.getString("cityName",StringUtils.EMPTY));
				row.createCell(10, Cell.CELL_TYPE_STRING).setCellValue(util.getString("areaName",StringUtils.EMPTY));
				row.createCell(11, Cell.CELL_TYPE_STRING).setCellValue(util.getString("payname",StringUtils.EMPTY));
			}
			row.createCell(12, Cell.CELL_TYPE_STRING).setCellValue(util.getString("sku",StringUtils.EMPTY));
			row.createCell(13, Cell.CELL_TYPE_STRING).setCellValue(util.getString("gname",StringUtils.EMPTY));
			row.createCell(14, Cell.CELL_TYPE_STRING).setCellValue(util.getString("cgname",StringUtils.EMPTY));
			row.createCell(15, Cell.CELL_TYPE_NUMERIC).setCellValue(util.getInteger("number"));
			row.createCell(16, Cell.CELL_TYPE_NUMERIC).setCellValue(util.getBigDecimal("buyPrice").doubleValue());
			i++;
		}
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment; filename=excel.xlsx");
		wb.write(res.getOutputStream());
	}
	
	@Channel(Constants.H)
	@RequestMapping("hlist.htm")
	@ResponseBody
	public ResBo<?> hlist(HttpServletRequest req){
		ReqBo reqBo = new ReqBo(req);
		int rows = reqBo.getParamInt("rows");
		if(rows > 50){
			return new ResBo<Pager<List<Map<String,Object>>>>(143L);
		}
		return new ResBo<Pager<List<Map<String, Object>>>>(
				orderSer.selectHOrders(null, reqBo.getParamLong("merchantId"), reqBo.getParamStr("orderNum"),
						reqBo.getParamInt("isPaid"),
						reqBo.getParamInt("state"),
						reqBo.getParamInt("status"),
						reqBo.getParamDate("sdate"),
						reqBo.getParamDate("edate"), reqBo.getParamInt("page"),
						rows));
	}
	
	@Channel(Constants.M)
	@RequestMapping("mrefund.htm")
	@ResponseBody
	public ResBo<?> refund(@RequestParam("orderId")long orderId){
		User user = (User) session.get(Constants.USERINFO);
		orderSer.updateOrders(orderId, user.getId(), null, null, EOrdersStatus.Status_6.getCode(), null);
		return new ResBo<Object>();
	}
	
	@Channel(Constants.B)
	@RequestMapping("brefundComplete.htm")
	@ResponseBody
	public ResBo<?> refundComplete(@RequestParam("orderId")long orderId){
		User user = (User) session.get(Constants.USERINFO);
		orderSer.updateOrders(orderId, null, user.getId(), null, EOrdersStatus.Status_7.getCode(), null);
		return new ResBo<Object>();
	}
}
