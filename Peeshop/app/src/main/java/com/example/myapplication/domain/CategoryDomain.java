package com.example.myapplication.domain;

public class CategoryDomain {
    private String title, picUrl;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryDomain(String title, String picUrl, int id) {
        this.title = title;
        this.picUrl = picUrl;
        this.id = id;
    }
    public CategoryDomain() {} //ham khong tham so bat buoc co Firebase Database để tự động chuyển đổi dữ liệu từ cơ sở dữ liệu sang đối tượng của lớp CategoryDomain.

}
