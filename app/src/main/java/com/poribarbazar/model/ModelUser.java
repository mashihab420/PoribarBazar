package com.poribarbazar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelUser {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;


    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("address")
    @Expose
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
