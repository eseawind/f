package com.f.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import framework.exception.BusinessException;

//分隔符@
public class Carts implements Serializable{

	private static final long serialVersionUID = -31074761305932629L;
	public static final String SEPARATOR_1 = "@";
	
	private List<Cart> cartList = new ArrayList<Cart>();
	
	public Carts(String carts){
		if(carts == null||carts.length() == 0){
			throw new BusinessException(112L);
		}
		String[] arr = carts.split(SEPARATOR_1);
		for(String cart:arr){
			cartList.add(new Cart(cart));
		}
	}
	
	public List<Cart> getCartList() {
		return cartList;
	}
	
	public int getCartsSize(){
		int number = 0;
		for(Cart cart:this.cartList){
			number = number + cart.getNumber();
		}
		return number;
	}
	
	public Cart getEqualsCart(Cart cart){
		for(Cart c:this.cartList){
			if(c.equals(cart)){
				return c;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=this.cartList.size();i<j;i++){
			sb.append(this.cartList.get(i).toString());
			if(i<j-1){
				sb.append(SEPARATOR_1);
			}
		}
		return sb.toString();
	}
	
}
