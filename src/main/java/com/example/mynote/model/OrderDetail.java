package com.example.mynote.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

@Entity
@IdClass(OrderProduct.class)
public class OrderDetail {
    @Id
    @Column(name = "OrderID",nullable = false)
    private Long orderId;
    @Id
    @Column(name = "ProductID",nullable = false)
    private Long productId;
    @Column(name = "UnitPrice",nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "Quantity",nullable = false)
    private short quantity;
    @Column(name = "Discount",nullable = false)
    private float discount;

    public OrderDetail() {}

    public OrderDetail(Long orderId, Long productId, BigDecimal unitPrice, short quantity, float discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public short getQuantity() {
        return quantity;
    }

    public float getDiscount() {
        return discount;
    }
}
