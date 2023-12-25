package com.ugurhmz.bookstore.helper;


import com.ugurhmz.bookstore.dto.responseDto.AuthorResponseDto;
import com.ugurhmz.bookstore.dto.responseDto.BookResponseDto;
import com.ugurhmz.bookstore.dto.responseDto.CategoryResponseDto;
import com.ugurhmz.bookstore.dto.responseDto.CityResponseDto;
import com.ugurhmz.bookstore.entities.Author;
import com.ugurhmz.bookstore.entities.Book;
import com.ugurhmz.bookstore.entities.Category;
import com.ugurhmz.bookstore.entities.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<BookResponseDto> bookToBookResDTOS(List<Book> books) {
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        books.forEach( book -> bookResponseDtoList.add(bookToBookResDTO(book)));

        return bookResponseDtoList;
    }

    public static AuthorResponseDto authorToAuthorResDTO(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());

        List<String> names = new ArrayList<>();
        author.getBookList().forEach( item -> names.add(item.getName()));
        authorResponseDto.setBookNames(names);
        authorResponseDto.setPostCodeName(author.getPostCode().getName());
        return authorResponseDto;
    }

    public static List<AuthorResponseDto> authorToAuthorResDTOS(List<Author> authorList) {
       List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
        authorList.forEach( author -> authorResponseDtoList.add(authorToAuthorResDTO(author)));
        return authorResponseDtoList;
    }

    public static CategoryResponseDto categoryToCategoryResponseDTO(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setCategoryName(category.getName());
        List<String> names = new ArrayList<>();

        category.getBookList().forEach( item -> names.add(item.getName()));
        categoryResponseDto.setBookNames(names);

        return categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoriesToCategoryResponseDTOS(List<Category> categories) {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        categories.forEach( category -> categoryResponseDtos.add(categoryToCategoryResponseDTO(category)) );
        return categoryResponseDtos;
    }

    public static CityResponseDto cityToCityResDTO(City city) {
        return new CityResponseDto(List.of(city.getName()));
    }

    public static CityResponseDto citiesToCityResDTO(List<City> cities) {
        List<String> cityNames = cities.stream()
                .map(City::getName)
                .collect(Collectors.toList());
        return new CityResponseDto(cityNames);
    }
}
