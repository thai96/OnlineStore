package com.example.mynote.controller;

import com.example.mynote.model.Category;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CategoryInfor;
import com.example.mynote.payload.UpdateRequest;
import com.example.mynote.service.CategoryService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryInfor>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<CategoryInfor> categories = categoryService.getListCategory(page, size);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/all/description")
    public ResponseEntity<List<Category>> getAllWithDescription(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<Category> categories = categoryService.getListCategoryWithDescription(page, size);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Category> insertCategory(
            @RequestBody Category category
    ){
        Category insertedCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(insertedCategory, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory(
            @RequestBody UpdateRequest<Category, Long> categories
    ){
        if(categories.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category category = categoryService.updateCategory(categories.getItemId(), categories.getNewInfor());
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCCategory(
            @RequestParam Long id
    ){
        ApiResponse response = categoryService.deleteCategory(id);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
