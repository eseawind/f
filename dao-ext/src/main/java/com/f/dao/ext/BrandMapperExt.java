package com.f.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.f.dto.goods.Brand;

public interface BrandMapperExt {

	public List<Brand> selBrand(@Param("merchantId")long merchantId,@Param("name")String name,@Param("start")int start,@Param("rows")int rows);
}
