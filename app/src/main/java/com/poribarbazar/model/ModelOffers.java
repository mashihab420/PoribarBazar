package com.poribarbazar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelOffers {

    @SerializedName("item_name")
    @Expose
    private String item_name;

    @SerializedName("discount_value")
    @Expose
    private String discount_value;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("image")
    @Expose
    private String image;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
