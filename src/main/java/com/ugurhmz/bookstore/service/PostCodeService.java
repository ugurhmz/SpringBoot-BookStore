package com.ugurhmz.bookstore.service;

import com.ugurhmz.bookstore.dto.requestDto.PostCodeRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.PostCodeResponseDto;
import com.ugurhmz.bookstore.entities.PostCode;
import org.springframework.stereotype.Service;


@Service
public interface PostCodeService {
    PostCodeResponseDto createPostCode(PostCodeRequestDto postCodeRequestDto);
    PostCodeResponseDto getPostCode(Long postCodeId);
    PostCodeRequestDto updatePostCode(Long postCodeId, PostCodeRequestDto postCodeRequestDto);
    String deletePostCode(Long postCodeId);
}
