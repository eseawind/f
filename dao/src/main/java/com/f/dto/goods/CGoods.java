package com.f.dto.goods;

import java.math.BigDecimal;
import java.util.Date;

public class CGoods {
    private Long id;

    private Long gid;

    private String cgname;

    private BigDecimal price;

    private BigDecimal mprice;

    private BigDecimal aprice;

    private Integer state;

    private Integer isDef;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getCgname() {
        return cgname;
    }

    public void setCgname(String cgname) {
        this.cgname = cgname == null ? null : cgname.trim();
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

    public BigDecimal getAprice() {
        return aprice;
    }

    public void setAprice(BigDecimal aprice) {
        this.aprice = aprice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsDef() {
        return isDef;
    }

    public void setIsDef(Integer isDef) {
        this.isDef = isDef;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}