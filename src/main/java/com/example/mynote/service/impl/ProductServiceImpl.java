package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Category;
import com.example.mynote.model.Product;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.ProductInfor;
import com.example.mynote.repositories.CategoryRepository;
import com.example.mynote.repositories.ProductRepository;
import com.example.mynote.service.ProductService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Product addProduct(ProductInfor product) {
        if(!categoryRepository.existsById(product.getCategory().getCategoryId())){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Category invalid");
            throw new BadRequestException(apiResponse);
        }
        if(!checkValidProductQuantity(product)){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Product quantity is invalid");
            throw new BadRequestException(apiResponse);
        }
        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Category not found!")));
        Product productEntity = mapper.map(product, Product.class);
        productEntity.setCategory(category);
        return productRepository.save(productEntity);
    }

    @Override
    public Product updateProduct(Long productId, ProductInfor newInfor) {
        if(!checkValidProductQuantity(newInfor)){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Quantity invalid");
            throw new BadRequestException(apiResponse);
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Product not found!")));
        product.setProductName(newInfor.getProductName());
        Category category = categoryRepository.findById(newInfor.getCategory().getCategoryId())
                .orElseThrow( () -> new ResourceNotFoundException("category not existed!"));
        product.setCategory(category);
        product.setQuantityPerUnit(newInfor.getQuantityPerUnit());
        product.setReOrderLevel(newInfor.getReOrderLevel());
        product.setUnitInStock(newInfor.getUnitInStock());
        product.setUnitOnOrder(newInfor.getUnitOnStock());
        product.setUnitPrice(newInfor.getUnitPrice());
        product.setDiscontinued(newInfor.getDiscontinued());
        product.setPicture(newInfor.getPicture());
        Product updatedProduct = productRepository.save(product);
        updatedProduct.getCategory().setProducts(null);
        return updatedProduct;
    }

    @Override
    public ApiResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Product not exist")));
        productRepository.delete(product);
        return new ApiResponse(Boolean.TRUE, "Delete successful", HttpStatus.OK);
    }

    @Override
    public ProductInfor getProduct(String productName) {
        if(productName.isBlank() || productName.isEmpty() || productName == null){
            throw new BadRequestException(new ApiResponse(Boolean.FALSE, "Input not valid"));
        }
        Product product = productRepository.findByProductName(productName.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return mapper.map(product, ProductInfor.class);
    }

    @Override
    public List<ProductInfor> getProductByCategory(String categoryName, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        if(!categoryRepository.existsByCategoryName(categoryName)){
            throw new BadRequestException(new ApiResponse(Boolean.FALSE, "Category not valid"));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "productName");
        Page<Product> productPages = productRepository.findAll(pageable);
        return productPages.stream()
                .filter(product -> product.getCategory().getCategoryName().equals(categoryName))
                .map(product -> mapper.map(product, ProductInfor.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfor> getAllProduct(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "productName");
        Page<Product> productPages = productRepository.findAll(pageable);
        int i = 0;
        return productPages.stream()
                .map(product -> mapper.map(product, ProductInfor.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfor> getHotItem() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, Comparator.comparingInt(p -> p.getOrderDetails().size()));
        return products.stream().map(product -> mapper.map(product, ProductInfor.class)).collect(Collectors.toList()).subList(0,7);
    }

    private Boolean checkValidProductQuantity(ProductInfor product){
        return product.getUnitInStock()>=0 && product.getUnitOnStock()>=0;
    }
}
