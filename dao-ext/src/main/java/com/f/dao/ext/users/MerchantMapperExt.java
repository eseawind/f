package com.f.dao.ext.users;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MerchantMapperExt {
   
	public List<Map<String,Object>> selMerchants(@Param("id")Long id,@Param("name")String name,@Param("start")int start,@Param("rows")int rows);
	
	public long countMerchants(@Param("id")Long id,@Param("name")String name);
	
	public List<Map<String,Object>> combobox(String q);
}