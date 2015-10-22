package com.f.services;

import com.f.cart.CartStr;

public interface ICarts {

	public CartStr selectCartStr(long userId);
	
	public void saveCartStr(CartStr cs);
}
