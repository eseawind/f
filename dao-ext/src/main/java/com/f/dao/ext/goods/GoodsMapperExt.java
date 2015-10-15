package com.f.dao.ext.goods;

import com.f.dto.goods.CGoods;
import com.f.dto.goods.GCategory;
import com.f.dto.goods.GStock;
import com.f.dto.goods.Goods;

public interface GoodsMapperExt {

	public int insertGoods(Goods g);
	
	public int insertCGoods(CGoods cg);
	
	public int insertGCategory(GCategory gc);
	
	public int insertGStock(GStock gs);
}
