package com.example.mynote.payload;

import com.example.mynote.model.Order;
import com.example.mynote.model.Product;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
public class OrderDetailInfo {
    private Long productId;
    private BigDecimal unitPrice;
    private short quantity;
    private float discount;
}
