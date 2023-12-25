package com.ugurhmz.bookstore.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {
    private Long id;
    private String categoryName;
    private List<String> bookNames;
}
