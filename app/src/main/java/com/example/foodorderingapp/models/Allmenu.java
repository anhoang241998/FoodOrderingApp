
package com.example.foodorderingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allmenu implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("deliveryTime")
    @Expose
    private String deliveryTime;
    @SerializedName("deliveryCharges")
    @Expose
    private String deliveryCharges;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("note")
    @Expose
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeString(this.rating);
        dest.writeString(this.deliveryTime);
        dest.writeString(this.deliveryCharges);
        dest.writeString(this.price);
        dest.writeString(this.note);
    }

    public Allmenu() {
    }

    protected Allmenu(Parcel in) {
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.rating = in.readString();
        this.deliveryTime = in.readString();
        this.deliveryCharges = in.readString();
        this.price = in.readString();
        this.note = in.readString();
    }

    public static final Parcelable.Creator<Allmenu> CREATOR = new Parcelable.Creator<Allmenu>() {
        @Override
        public Allmenu createFromParcel(Parcel source) {
            return new Allmenu(source);
        }

        @Override
        public Allmenu[] newArray(int size) {
            return new Allmenu[size];
        }
    };
}
