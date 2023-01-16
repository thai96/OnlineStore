package com.example.mynote.model;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private int orderId;
    private int productId;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }
}
