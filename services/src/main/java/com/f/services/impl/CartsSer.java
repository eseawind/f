package com.f.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f.cart.CartStr;
import com.f.dao.ext.CartMapperExt;
import com.f.services.ICarts;

import framework.exception.BusinessException;

@Service
public class CartsSer implements ICarts{
	
	@Autowired
	private CartMapperExt ext;

	@Override
	public CartStr selectCartStr(long userId) {
		CartStr cs = ext.selCartStr(userId);
		if(cs == null){
			cs = new CartStr();
			cs.setUserId(userId);
		}
		return cs;
	}

	@Override
	public void saveCartStr(CartStr cs) {
		int i = 0;
		if(ext.isExist(cs.getUserId())){
			i = ext.updateCartStr(cs);
		}else{
			i = ext.insertCartStr(cs);
		}
		if(i != 1){
			throw new BusinessException(113L);
		}
	}

}
