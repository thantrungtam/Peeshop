package com.example.myapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.security.ProtectionDomain;
import java.util.ArrayList;

//extends ke thua tu class
//implements ke thua tu ca interface


//Parcelable là một giao diện trong Android cho phép các đối tượng của lớp thực hiện nó có thể được
// chuyển qua lại giữa các thành phần Android thông qua Intent hoặc Bundle
public class ItemsDomain implements Parcelable {
    private String title, description;
    private ArrayList<String> picUrl;
    private double price, oldPrice, rating;
    private int review, numberInCart;

    public ItemsDomain() {
    }

//contructor
    public ItemsDomain(String title, String description, ArrayList<String> picUrl,
                       double rating, double price, double oldPrice, int review) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
        this.oldPrice = oldPrice;
        this.review = review;
        this.rating = rating;
    }
    /*Khoi phuc du lieu tu Parcel
    * . Parcel là một đối tượng chứa dữ liệu đã được đóng gói để chuyển qua lại giữa các thành phần Android.*/
    protected ItemsDomain(Parcel in){
        title = in.readString();
        description = in.readString();
        picUrl = in.createStringArrayList();
        price = in.readDouble();
        oldPrice = in.readDouble();
        rating = in.readDouble();
        review = in.readInt();
        numberInCart=in.readInt();
    }

    /*dong goi du lieu vap parcel, can viet du lieu theo dung thu tu nhu khi doc du lieu trong ItemsDomain*/

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeStringList(picUrl);
        parcel.writeDouble(price);
        parcel.writeDouble(oldPrice);
        parcel.writeDouble(rating);
        parcel.writeInt(review);
        parcel.writeInt(numberInCart);
    }



    /*public static final Creator<ItemsDomain> CREATOR: Đây là một trường static final cần thiết để
    Android có thể tạo ra các đối tượng ItemsDomain từ Parcel. Creator là một giao diện với hai phương thức:
    createFromParcel(Parcel in) để tạo một đối tượng từ Parcel, và newArray(int size) để tạo một mảng các đối tượng.*/
    public static final Creator<ItemsDomain> CREATOR = new Creator<ItemsDomain>() {
        @Override
        public ItemsDomain createFromParcel(Parcel in) {
            return new ItemsDomain(in);
        }

        @Override
        public ItemsDomain[] newArray(int size) {
            return new ItemsDomain[0];
        }
    };

    //getter va setter: alt + insert -> getter& setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(ArrayList<String> picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }



    @Override
    public int describeContents() {
        //Thuong tra ve 0,
        return 0;
    }

}
