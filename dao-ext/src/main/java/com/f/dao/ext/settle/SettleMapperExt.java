package com.f.dao.ext.settle;

import java.util.List;

import com.f.cart.SSettleGoods;

public interface SettleMapperExt {

	public List<SSettleGoods> selectSSettleGoods(List<Long> cgids);
}
