package com.ugurhmz.bookstore.helper;


import com.ugurhmz.bookstore.dto.responseDto.BookResponseDto;
import com.ugurhmz.bookstore.entities.Author;
import com.ugurhmz.bookstore.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static BookResponseDto bookToBookResDTO(Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setName(book.getName());
        bookResponseDto.setCategoryName(book.getCategory().getName());

        List<String> names = new ArrayList<>();

        book.getAuthorList().forEach( author -> names.add(author.getName()));
        bookResponseDto.setAuthorNames(names);
        return bookResponseDto;
    }
}
