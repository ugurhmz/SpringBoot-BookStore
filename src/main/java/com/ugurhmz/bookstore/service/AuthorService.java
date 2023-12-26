package com.ugurhmz.bookstore.service;

import com.ugurhmz.bookstore.dto.requestDto.AuthorRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.AuthorResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);

}
