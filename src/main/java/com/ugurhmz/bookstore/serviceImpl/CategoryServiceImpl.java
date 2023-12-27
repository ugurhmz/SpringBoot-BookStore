package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.CategoryRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResWithOutBookNamesDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResponseDto;
import com.ugurhmz.bookstore.entities.Category;
import com.ugurhmz.bookstore.helper.Mapper;
import com.ugurhmz.bookstore.repository.CategoryRepository;
import com.ugurhmz.bookstore.service.CategoryService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResWithOutBookNamesDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category newCategory = new Category();
        String checkedCategoryName = Optional.ofNullable(categoryRequestDto.getName())
                .map(String::trim)
                .filter( name -> !name.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Category name cannot be empty or null !!"));
        newCategory.setName(checkedCategoryName);

        Category savedCategory = categoryRepository.save(newCategory);

        CategoryResWithOutBookNamesDto responseDto = new CategoryResWithOutBookNamesDto();
        responseDto.setId(savedCategory.getId());
        responseDto.setCategoryName(savedCategory.getName());
        return responseDto;
    }
}
