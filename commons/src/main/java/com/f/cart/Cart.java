package com.f.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import framework.exception.BusinessException;


//字符串表示 Y|N_cgid#cgid#cgId_number_type1#type2@ 
public class Cart implements Serializable{

	private static final long serialVersionUID = -1599236470983369139L;
	public static final String SEPARATOR_1 = "_";
	public static final String SEPARATOR_2 = "#";
	public static final String SEPARTOR_Y = "Y";
	public static final String SEPARTOR_N = "N";
	
	private List<Long> cgidList = new ArrayList<Long>(3);
	private int number = 1;
	private boolean checked = true;
	
	public Cart(String cart){
		if(cart == null||cart.length() == 0){
			throw new BusinessException(112L);
		}
		String[] arr = cart.split(SEPARATOR_1);
		if(SEPARTOR_Y.equals(arr[0])){
			this.checked = true;
		}else{
			this.checked = false;
		}
		for(String cgid:arr[1].split(SEPARATOR_2)){
			this.cgidList.add(Long.parseLong(cgid));
		}
		this.number = Integer.parseInt(arr[2]);
		sort();
	}
	
	public Cart(int number, Long ... cgids){
		this.number = number <= 0?1:number;
		if(cgids == null||cgids.length == 0){
			throw new BusinessException(112L);
		}
		for(Long l:cgids){
			if(l != null){
				this.cgidList.add(l);
			}
		}
		sort();
	}
	
	public Cart(int number, String ... cgids){
		this.number = number <= 0?1:number;
		if(cgids == null||cgids.length == 0){
			throw new BusinessException(112L);
		}
		for(String cgid:cgids){
			if(cgid != null){
				this.cgidList.add(Long.valueOf(cgid));
			}
		}
		sort();
	}
	
	private void sort(){
		if(this.cgidList.size() > 1){
			Collections.sort(this.cgidList, new Comparator<Long>(){
				public int compare(Long o1, Long o2) {
					return o1.compareTo(o2);
				}
			});
		}
	}
	
	public void plus(){
		this.number++;
	}
	
	public void minus(){
		this.number--;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<Long> getCgidList() {
		return cgidList;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(toCartString());
		sb.append(SEPARATOR_1);
		sb.append(this.number);
		return sb.toString();
	}
	
	public String toCartString(){
		StringBuilder sb = new StringBuilder(this.checked?SEPARTOR_Y:SEPARTOR_N);
		sb.append(SEPARATOR_1);
		for(int i = 0,j=this.cgidList.size();i<j;i++){
			sb.append(this.cgidList.get(i));
			if(i<j-1){
				sb.append(SEPARATOR_2);
			}
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return toCartString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cart){
			Cart cart = (Cart) obj;
			return toCartString().equals(cart.toCartString());
		}
		return false;
	}
	
	public boolean isChecked(){
		return this.checked;
	}
}
