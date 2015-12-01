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
import com.f.dto.Area;
import com.f.services.IArea;

import framework.web.ResBo;
import framework.web.auth.Channel;
import framework.web.auth.IsLogin;


@Controller
@RequestMapping("area")
public class AreaController {
	
	@Autowired
	private IArea areaSer;

	@IsLogin(false)
	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<?> selAreas(@RequestParam(value="pid",defaultValue="0")int pid){
		return new ResBo<List<Area>>(areaSer.selAreaByPID(pid,true));
	}
	
	@IsLogin(false)
	@RequestMapping("hlist.htm")
	@ResponseBody
	public ResBo<?> selHAreas(@RequestParam(value="pid",defaultValue="0")int pid){
		return new ResBo<List<Area>>(areaSer.selAreaByPID(pid,false));
	}
	
	@IsLogin(false)
	@RequestMapping("combobox.htm")
	@ResponseBody
	public ResBo<?> selAreasCombobox(@RequestParam(value="pid",defaultValue="0")int pid){
		return new ResBo<List<Combobox>>(areaSer.selAreasComboboxByPID(pid));
	}
	
	@Channel(Constants.H)
	@RequestMapping("addOrUpd.htm")
	@ResponseBody
	public ResBo<?> addOrUpd(@ModelAttribute Area area){
		if(area.getId() == null){
			areaSer.insertArea(area);
		}else{
			areaSer.updateArea(area);
		}
		return new ResBo<Object>();
	}
	
}
