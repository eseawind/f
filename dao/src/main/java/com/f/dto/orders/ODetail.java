package com.f.dto.orders;

import java.math.BigDecimal;

public class ODetail {
    private Long id;

    private Long orderId;

    private Long cgoodsId;

    private BigDecimal buyPrice;

    private Integer number;

    private String sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCgoodsId() {
        return cgoodsId;
    }

    public void setCgoodsId(Long cgoodsId) {
        this.cgoodsId = cgoodsId;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }
}