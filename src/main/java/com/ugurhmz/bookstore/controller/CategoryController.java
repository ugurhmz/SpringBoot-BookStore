package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.CategoryRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResWithOutBookNamesDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResponseDto;
import com.ugurhmz.bookstore.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody final CategoryRequestDto categoryRequestDto) {
        try {
            CategoryResWithOutBookNamesDto categoryResWithOutBookNamesDto = categoryService.createCategory(categoryRequestDto);
            return new ResponseEntity<>(Collections.singletonMap("message", categoryResWithOutBookNamesDto), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            String errMsg = "Error while creating!!";
            return new ResponseEntity<>(Collections.singletonMap("error", errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
