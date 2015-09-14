package com.f.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.f.dto.Area;
import com.f.services.IArea;

import framework.web.ResBo;


@Controller
@RequestMapping("area")
public class AreaController {
	
	@Autowired
	private IArea areaSer;

	@RequestMapping("list.htm")
	@ResponseBody
	public ResBo<?> selAreas(@RequestParam(value="pid",defaultValue="0")int pid){
		return new ResBo<List<Area>>(areaSer.selAreaByPID(pid));
	}
	
	
}
