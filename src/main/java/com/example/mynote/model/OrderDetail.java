package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(OrderProduct.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`Order Details`")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "ProductID",nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Product product;
    @Column(name = "UnitPrice",nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "Quantity",nullable = false)
    private short quantity;
    @Column(name = "Discount",nullable = false)
    private float discount;
}
