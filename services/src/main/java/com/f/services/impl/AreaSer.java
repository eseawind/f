package com.f.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.commons.Combobox;
import com.f.dao.AreaMapper;
import com.f.dao.ext.AreaMapperExt;
import com.f.dto.Area;
import com.f.services.IArea;

@Service
public class AreaSer implements IArea{
	
	@Autowired
	private AreaMapperExt areaExt;
	@Autowired
	private AreaMapper amapper;

	@Override
	public List<Area> selAreaByPID(int pid,boolean filter) {
		return areaExt.selAreasByPID(pid,filter);
	}

	@Override
	public List<Combobox> selAreasComboboxByPID(int pid) {
		return areaExt.selAreasComboboxByPID(pid);
	}

	@Override
	public void insertArea(Area area) {
		area.setId(null);
		amapper.insertSelective(area);
	}

	@Override
	public void updateArea(Area area) {
		amapper.updateByPrimaryKeySelective(area);
	}

}
