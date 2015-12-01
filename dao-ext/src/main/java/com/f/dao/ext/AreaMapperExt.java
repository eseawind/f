package com.f.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.f.commons.Combobox;
import com.f.dto.Area;

public interface AreaMapperExt {

	public List<Area> selAreasByPID(@Param("pid")int pid,@Param("filter") boolean filter);
	
	public List<Combobox> selAreasComboboxByPID(int pid);
}
