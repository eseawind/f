package com.f.dto.goods;

public class GStock {
    private Long id;

    private Long cgid;

    private Integer number;

    private Integer anumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCgid() {
        return cgid;
    }

    public void setCgid(Long cgid) {
        this.cgid = cgid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAnumber() {
        return anumber;
    }

    public void setAnumber(Integer anumber) {
        this.anumber = anumber;
    }
}