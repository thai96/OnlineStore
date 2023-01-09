package com.example.mynote.model;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long categoryId;
    @Column(name = "CategoryName", nullable = false, length = 15)
    public String categoryName;
    @Column(name = "Description")
    public String description;
    @Column(name = "Picture")
    public byte[] picture;

    public Category() {}

    public Category(Long categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
