package com.example.mynote.service;

import com.example.mynote.model.Category;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CategoryInfor;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category newInfor);
    ApiResponse deleteCategory(Long id);
    List<CategoryInfor> getListCategory(int page, int size);
    List<Category> getListCategoryWithDescription(int page, int size);
}
