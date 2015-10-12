package com.f.dto.goods;

public class GDetail {
    private Long id;

    private Long cgid;

    private String photo;

    private String photo1;

    private String photo2;

    private String photo3;

    private String photo4;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1 == null ? null : photo1.trim();
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2 == null ? null : photo2.trim();
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3 == null ? null : photo3.trim();
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4 == null ? null : photo4.trim();
    }
}