package com.f.dao.ext.settle;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.f.cart.SSettleGoods;

public interface SettleMapperExt {

	public List<SSettleGoods> selectSSettleGoods(@Param("cgids")List<Long> cgids,@Param("merchantId")long merchantId);
}
