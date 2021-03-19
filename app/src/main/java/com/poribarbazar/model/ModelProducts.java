package com.poribarbazar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProducts {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("p_name")
    @Expose
    private String pName;
    @SerializedName("p_description")
    @Expose
    private String pDescription;
    @SerializedName("p_price")
    @Expose
    private String pPrice;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("discount_price")
    @Expose
    private String discountPrice;
    @SerializedName("dicount_percentage")
    @Expose
    private String dicountPercentage;
    @SerializedName("flash_sell")
    @Expose
    private String flashSell;
    @SerializedName("polular")
    @Expose
    private String polular;


    @SerializedName("sub_category")
    @Expose
    private String sub_category;

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPDescription() {
        return pDescription;
    }

    public void setPDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getPPrice() {
        return pPrice;
    }

    public void setPPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDicountPercentage() {
        return dicountPercentage;
    }

    public void setDicountPercentage(String dicountPercentage) {
        this.dicountPercentage = dicountPercentage;
    }

    public String getFlashSell() {
        return flashSell;
    }

    public void setFlashSell(String flashSell) {
        this.flashSell = flashSell;
    }

    public String getPolular() {
        return polular;
    }

    public void setPolular(String polular) {
        this.polular = polular;
    }

}
