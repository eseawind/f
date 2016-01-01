package com.f.dao.ext.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.f.dto.goods.Dcgoods;
import com.f.dto.goods.Dynpage;

public interface DynpageMapperExt {

	public List<Map<String,Object>> selectDynpage(@Param("merchantId")Long merchantId,@Param("type")Integer type,@Param("start")int start,@Param("rows")int rows);
	
	public List<Map<String,Object>> selectDcgoods(@Param("merchantId")Long merchantId,@Param("pageId")Long pageId,@Param("type")Integer type,@Param("start")int start,@Param("rows")int rows);
	
	public List<Long> selectDcgoodsId(@Param("merchantId")Long merchantId,@Param("pageId")Long pageId,@Param("type")Integer type,@Param("start")int start,@Param("rows")int rows);
	
	public long countDcgoods(@Param("merchantId")Long merchantId,@Param("pageId")Long pageId,@Param("type")Integer type);
	
	public long countDynpage(@Param("merchantId")Long merchantId,@Param("type")Integer type);
	
	public int insertDynpage(Dynpage dynpage);
	
	public int insertDcgoods(List<Dcgoods> dcgs);
	
}
