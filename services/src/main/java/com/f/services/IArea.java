package com.f.services;

import java.util.List;

import com.f.commons.Combobox;
import com.f.dto.Area;

public interface IArea {

	public List<Area> selAreaByPID(int pid,boolean filter);
	
	public List<Combobox> selAreasComboboxByPID(int pid);
	
	public void insertArea(Area area);
	
	public void updateArea(Area area);
}
