package com.f.dto.users;

public class Favorite {
    private Long id;

    private Long userId;

    private Long cgoodsId;

    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCgoodsId() {
        return cgoodsId;
    }

    public void setCgoodsId(Long cgoodsId) {
        this.cgoodsId = cgoodsId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}