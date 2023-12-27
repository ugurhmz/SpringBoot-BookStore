package com.ugurhmz.bookstore.service;

import com.ugurhmz.bookstore.dto.requestDto.CategoryRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResWithOutBookNamesDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryResWithOutBookNamesDto createCategory(CategoryRequestDto categoryRequestDto);

}
