package com.example.mynote.utils;

import com.example.mynote.exception.ShopApiException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class AppUtils {
    public static void validatePageNumberAndSize(int page, int size){
        if(page < 0){
            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Page number cannot be smaller than 0");
        }
        if(size < 0){
            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Page size cannot be smaller than 0");
        }
        if(size > AppConstant.MAX_PAGE_SIZE){
            throw new ShopApiException(HttpStatus.BAD_REQUEST, "Page size cannot bigger than "+ AppConstant.MAX_PAGE_SIZE);
        }
    }
}
