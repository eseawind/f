package com.f.dao.ext;

import org.apache.ibatis.annotations.Param;

public interface KeyMapperExt {

	public int updateKey(@Param("table")String table,@Param("skip")int skip);
	
	public int insertKey(String table);
	
	public long selKey(String table);
}
