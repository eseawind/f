package com.f.dao.ext.goods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.f.commons.GoodsDynInfo;
import com.f.commons.GoodsStaInfo;
import com.f.dto.goods.CGoods;
import com.f.dto.goods.Goods;

public interface GoodsMapperExt {

	public int insertGoods(Goods g);
	
	public int insertCGoods(CGoods cg);
	
	public List<Map<String,Object>> selCGoods(@Param("id")Long cgid,@Param("sku")String sku,@Param("gname")String gname ,@Param("merchantId")Long merchantId,@Param("brandId")Long brandId,@Param("start")int start,@Param("rows")int rows);
	
	public long countCGoods(@Param("id")Long cgid,@Param("sku")String sku,@Param("gname")String gname ,@Param("merchantId")Long merchantId,@Param("brandId")Long brandId);
	
	public Long selGIdByCgId(long cgid);
	
	public List<GoodsStaInfo> selectCGoodsStaInfoByCgIds(List<Long> cgids);
	
	public List<GoodsDynInfo> selectCGoodsDynInfo(List<Long> cgids);
	
	public List<GoodsStaInfo> selectCGoodsStaInfo(@Param("merchantId")Long merchantId,@Param("code")String code,@Param("filter") Integer filter,@Param("sort") Integer sort,@Param("start")int start,@Param("rows")int rows);
	
	public long countCGoodsStaInfo(@Param("merchantId")Long merchantId,@Param("code")String code,@Param("filter") Integer filter);
	
	public List<GoodsStaInfo> selectCGoodsStaInfoByGId(long gid);
}
