package com.example.mynote.service;

import com.example.mynote.model.Product;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.ProductInfor;

import java.util.List;


public interface ProductService {
    Product addProduct(ProductInfor product);
    Product updateProduct(Long productId, ProductInfor newInfor);
    ApiResponse deleteProduct(Long id);
    ProductInfor getProduct(String productName);
    List<ProductInfor> getProductByCategory(String categoryName,int page, int size);
    List<ProductInfor> getAllProduct(int page, int size);
    List<ProductInfor> getHotItem();
}
