package com.example.mynote.service;

import com.example.mynote.model.Category;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CategoryInfor;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category oldInfor, Category newInfor);
    ApiResponse deleteCategory(Long id);
    List<CategoryInfor> getListCategory();
    List<Category> getListCategoryWithDescription();
}
