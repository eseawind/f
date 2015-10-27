package com.f.services;

import java.util.List;

import com.f.commons.Combobox;
import com.f.dto.Area;

public interface IArea {

	public List<Area> selAreaByPID(int pid);
	
	public List<Combobox> selAreasComboboxByPID(int pid);
}
