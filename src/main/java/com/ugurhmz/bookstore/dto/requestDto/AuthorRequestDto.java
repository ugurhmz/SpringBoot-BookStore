package com.ugurhmz.bookstore.dto.requestDto;


import lombok.Data;

import java.util.List;

@Data
public class AuthorRequestDto {
    private String name;
    private Long postCode;
    private List<Long> bookIds;
}
