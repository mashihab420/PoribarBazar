package com.poribarbazar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelOrders {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("p_price")
    @Expose
    private String p_price;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("quantity")
    @Expose
    private String quantity;

    @SerializedName("order_time")
    @Expose
    private String order_time;

    @SerializedName("order_status")
    @Expose
    private String order_status;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("subtotal")
    @Expose
    private String subtotal;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("shipping_fee")
    @Expose
    private String shipping_fee;

    @SerializedName("pay_method")
    @Expose
    private String pay_method;

    @SerializedName("invoice_id")
    @Expose
    private String invoice_id;

    @SerializedName("p_name")
    @Expose
    private String p_name;

    @SerializedName("payment_phone")
    @Expose
    private String payment_phone;

    @SerializedName("trx_id")
    @Expose
    private String trx_id;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPayment_phone() {
        return payment_phone;
    }

    public void setPayment_phone(String payment_phone) {
        this.payment_phone = payment_phone;
    }

    public String getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(String trx_id) {
        this.trx_id = trx_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
