package com.f.dao.ext;

import java.util.List;

import com.f.commons.Combobox;
import com.f.dto.Area;

public interface AreaMapperExt {

	public List<Area> selAreasByPID(int pid);
	
	public List<Combobox> selAreasComboboxByPID(int pid);
}
