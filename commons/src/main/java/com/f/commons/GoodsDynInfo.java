package com.f.commons;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsDynInfo implements Serializable{

	private static final long serialVersionUID = -769975092936284169L;

	private Long cgid;
	private Long gid;
	private Long merchantId;
	private BigDecimal price;
	private BigDecimal mprice;
	private int number;
	public Long getCgid() {
		return cgid;
	}
	public void setCgid(Long cgid) {
		this.cgid = cgid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMprice() {
		return mprice;
	}
	public void setMprice(BigDecimal mprice) {
		this.mprice = mprice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
