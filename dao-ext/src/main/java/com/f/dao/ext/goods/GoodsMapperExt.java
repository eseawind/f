package com.f.dao.ext.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.f.dto.goods.CGoods;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;

public interface GoodsMapperExt {

	public int insertGoods(Goods g);
	
	public int insertCGoods(CGoods cg);
	
	public int insertGStock(GStock gs);
	
	public List<Map<String,Object>> selCGoods(@Param("id")Long cgid,@Param("sku")String sku,@Param("gname")String gname ,@Param("merchantId")Long merchantId,@Param("brandId")Long brandId,@Param("start")int start,@Param("rows")int rows);
	
	public long countCGoods(@Param("id")Long cgid,@Param("sku")String sku,@Param("gname")String gname ,@Param("merchantId")Long merchantId,@Param("brandId")Long brandId);
}
