package com.f.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.dao.ext.AreaMapperExt;
import com.f.dto.Area;
import com.f.services.IArea;

@Service
public class AreaSer implements IArea{
	
	@Autowired
	private AreaMapperExt areaExt;

	@Override
	public List<Area> selAreaByPID(int pid) {
		return areaExt.selAreasByPID(pid);
	}

}
