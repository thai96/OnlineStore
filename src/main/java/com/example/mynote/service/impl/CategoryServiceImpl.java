package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.model.Category;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CategoryInfor;
import com.example.mynote.repositories.CategoryRepository;
import com.example.mynote.service.CategoryService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Category addCategory(Category category) {
        if(categoryRepository.existsByCategoryName(category)){
            ApiResponse response = new ApiResponse(Boolean.FALSE,"Category is existed");
            throw new BadRequestException(response);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category oldInfor, Category newInfor) {
        Category category = categoryRepository.findById(oldInfor.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        category.setCategoryName(newInfor.getCategoryName());
        category.setDescription(newInfor.getDescription());
        category.setPicture(newInfor.getPicture());
        return categoryRepository.save(category);
    }

    @Override
    public ApiResponse deleteCategory(Long id) {
        if(categoryRepository.existsById(id)){
            ApiResponse response = new ApiResponse(Boolean.FALSE,"Category is existed");
            throw new BadRequestException(response);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse(Boolean.TRUE,"Delete category successfully");
    }

    @Override
    public List<CategoryInfor> getListCategory(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable categoryPages = PageRequest.of(page, size, Sort.Direction.DESC,"categoryName");
        Page<Category> categories = categoryRepository.findAll(categoryPages);
        List<CategoryInfor> categoriesInfor = categories.stream().map(category -> mapper.map(category, CategoryInfor.class)).collect(Collectors.toList());
        return categoriesInfor;
    }

    @Override
    public List<Category> getListCategoryWithDescription(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable categoryPages = PageRequest.of(page, size, Sort.Direction.DESC,"categoryName");
        Page<Category> categories = categoryRepository.findAll(categoryPages);
        return categories.toList();
    }
}
