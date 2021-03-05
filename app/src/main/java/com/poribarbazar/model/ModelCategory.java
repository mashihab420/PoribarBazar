package com.poribarbazar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCategory {

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    public String getCategory() {
        return category_name;
    }

    public void setCategory(String category) {
        this.category_name = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
