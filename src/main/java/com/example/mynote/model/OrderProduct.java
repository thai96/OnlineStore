package com.example.mynote.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderProduct implements Serializable {
    private int orderId;
    private int productId;
}
