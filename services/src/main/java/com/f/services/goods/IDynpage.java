package com.f.services.goods;

import java.util.List;
import java.util.Map;

import com.f.dto.goods.Dcgoods;
import com.f.dto.goods.Dynpage;

import framework.web.Pager;

public interface IDynpage {

	public Pager<List<Map<String,Object>>> selectDynpage(Long merchantId,Integer type,int page,int rows);
	
	public Pager<List<Long>> selectDcgoods(Long merchantId,Long pageId,Integer type,int page,int rows);
	
	public void insertDynpage(Dynpage page,List<Dcgoods> list);
	
	public void updateDynpage(Dynpage page);
	
	public void updateDcgoods(Dcgoods dcg);
}
