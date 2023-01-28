package com.example.mynote.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(OrderProduct.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order Details")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "ProductID",nullable = false)
    private Product product;
    @Column(name = "UnitPrice",nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "Quantity",nullable = false)
    private short quantity;
    @Column(name = "Discount",nullable = false)
    private float discount;
}
