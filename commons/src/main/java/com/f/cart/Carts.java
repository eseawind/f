package com.f.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//分隔符@
public class Carts implements Serializable{

	private static final long serialVersionUID = -31074761305932629L;
	public static final String SEPARATOR_1 = "@";
	
	private List<Cart> cartList = new ArrayList<Cart>();
	
	public Carts(){
		
	}
	
	public Carts(String carts){
		if(carts == null||carts.length() == 0){
			return;
		}
		try{
			String[] arr = carts.split(SEPARATOR_1);
			for(String cart:arr){
				cartList.add(new Cart(cart));
			}
		}catch(Exception e){
			e.printStackTrace();
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
	
	public Cart getEqualsCart(String cartStr){
		for(Cart c:this.cartList){
			if(c.toCartString().equals(cartStr)){
				return c;
			}
		}
		return null;
	}
	
	public void deleteCart(String cartStr){
		int index = -1;
		for(int i=0,j=this.cartList.size();i<j;i++){
			if(this.cartList.get(i).toCartString().equals(cartStr)){
				index = i;
				break;
			}
		}
		if(index > -1){
			this.cartList.remove(index);
		}
	}
	
	public void deleteCart(Cart cart){
		this.cartList.remove(cart);
	}
	
	public void addCart(Cart cart){
		Cart c = getEqualsCart(cart);
		if(c == null){
			this.cartList.add(cart);
		}else{
			c.setNumber(c.getNumber() + cart.getNumber());
		}
	}
	
	public void updCart(Cart cart){
		Cart c = getEqualsCart(cart);
		if(c == null){
			this.cartList.add(cart);
		}else{
			c.setNumber(cart.getNumber());
		}
	}
	
	public void updCart(String cartStr,int number){
		Cart c = getEqualsCart(cartStr);
		if(c != null){
			c.setNumber(number > 1?number:1);
		}
	}
	
	public void merge(Carts carts){
		if(carts.getCartsSize() == 0)return;
		if(this.getCartsSize() == 0){
			this.cartList.addAll(carts.getCartList());
			return;
		}
		boolean isExist = false;
		for(Cart cart:carts.getCartList()){
			isExist = false;
			for(Cart c:this.cartList){
				if(cart.equals(c)){
					isExist = true;
					c.setNumber(c.getNumber() + cart.getNumber());
					break;
				}
			}
			if(!isExist){
				this.cartList.add(cart);
			}
		}
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
	
	public Map<Long, Carts> groupByMerchant(){
		Map<Long, Carts> map = new HashMap<Long, Carts>();
		Carts carts = null;
		for(Cart cart:this.cartList){
			carts = map.get(cart.getMerchantId());
			if(carts == null){
				carts = new Carts();
				map.put(cart.getMerchantId(), carts);
			}
			carts.getCartList().add(cart);
		}
		return map;
	}
}
