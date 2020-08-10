
package com.example.foodorderingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class Allmenu {

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    public String name;

    @ColumnInfo(name = "image_url")
    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    @Expose
    public String rating;

    @ColumnInfo(name = "delivery_time")
    @SerializedName("deliveryTime")
    @Expose
    public String deliveryTime;

    @ColumnInfo(name = "delivery_charges")
    @SerializedName("deliveryCharges")
    @Expose
    public String deliveryCharges;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    @Expose
    public String price;

    @ColumnInfo(name = "note")
    @SerializedName("note")
    @Expose
    public String note;

    @PrimaryKey(autoGenerate = true)
    public int uuid;

    public Allmenu(String name, String imageUrl, String rating, String deliveryTime, String deliveryCharges, String price, String note) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.deliveryCharges = deliveryCharges;
        this.price = price;
        this.note = note;
    }

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


}
