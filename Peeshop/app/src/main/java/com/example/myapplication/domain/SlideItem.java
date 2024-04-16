package com.example.myapplication.domain;
//Class SlideItem này đại diện cho một mục trượt trong danh sách các mục trượt
public class SlideItem {
   private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SlideItem(String url) {
        this.url = url;
    }

    public SlideItem(){

   }
}
