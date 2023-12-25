package com.ugurhmz.bookstore.dto.requestDto;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private Long postCode;
}
