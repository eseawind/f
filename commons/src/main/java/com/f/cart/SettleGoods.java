package com.f.cart;

import java.io.Serializable;
import java.math.BigDecimal;

public class SettleGoods implements Serializable{

	private static final long serialVersionUID = 2647396984778724747L;
	
	private Long gid;
	private Long cgid;
	private int stockNum;//库存数量
	private BigDecimal price;
	private String photo;
	private String gname;
	private String cgname;
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Long getCgid() {
		return cgid;
	}
	public void setCgid(Long cgid) {
		this.cgid = cgid;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getCgname() {
		return cgname;
	}
	public void setCgname(String cgname) {
		this.cgname = cgname;
	}
	
}
