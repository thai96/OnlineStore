package com.example.mynote.controller;

import com.example.mynote.model.Product;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.ProductInfor;
import com.example.mynote.payload.UpdateRequest;
import com.example.mynote.service.ProductService;
import com.example.mynote.utils.AppConstant;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductInfor>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<ProductInfor> products = productService.getAllProduct(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductInfor>> getProductByCategory(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "category") String categoryName
    ){
        List<ProductInfor> products = productService.getProductByCategory(categoryName, page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/hot")
    public ResponseEntity<List<ProductInfor>> getHotItem(){
        List<ProductInfor> products = productService.getHotItem();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/information")
    public ResponseEntity<ProductInfor> getProduct(@RequestParam(value = "name") String productName)
    {
        ProductInfor product = productService.getProduct(productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateRequest<ProductInfor> request){
        if(request.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.updateProduct(request.getOldInfor(), request.getNewInfor());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Product> insertProduct(@RequestBody ProductInfor productInfor){
        Product product = productService.addProduct(productInfor);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "id") Long id){
        ApiResponse response = productService.deleteProduct(id);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
