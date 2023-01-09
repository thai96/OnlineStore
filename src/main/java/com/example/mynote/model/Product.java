package com.example.mynote.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "ProductName", length = 40, nullable = false)
    private String productName;
    @Column(name = "CategoryID")
    private Long categoryId;
    @Column(name = "QuantityPerUnit",length = 20)
    private String quantityPerUnit;
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "UnitInStock")
    private short unitInStock;
    @Column(name = "UnitOnStock")
    private short unitOnStock;
    @Column(name = "ReorderLevel")
    private short reOrderLevel;
    @Column(name = "Discontinued")
    private Boolean discontinued;
    @Column(name = "Picture")
    private byte[] picture;

    public Product() {}

    public Product(String productName, Long categoryId, String quantityPerUnit, BigDecimal unitPrice, short unitInStock, short unitOnStock, short reOrderLevel, Boolean discontinued, byte[] picture) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitInStock = unitInStock;
        this.unitOnStock = unitOnStock;
        this.reOrderLevel = reOrderLevel;
        this.discontinued = discontinued;
        this.picture = picture;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitInStock(short unitInStock) {
        this.unitInStock = unitInStock;
    }

    public void setUnitOnStock(short unitOnStock) {
        this.unitOnStock = unitOnStock;
    }

    public void setReOrderLevel(short reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public short getUnitInStock() {
        return unitInStock;
    }

    public short getUnitOnStock() {
        return unitOnStock;
    }

    public short getReOrderLevel() {
        return reOrderLevel;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public byte[] getPicture() {
        return picture;
    }
}
