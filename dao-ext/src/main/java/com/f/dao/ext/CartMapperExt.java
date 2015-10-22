package com.f.dao.ext;

import com.f.cart.CartStr;

public interface CartMapperExt {

	public CartStr isExist(long userId);
	
	public int insertCartStr(CartStr cs);
	
	public int updateCartStr(CartStr cs);
}
