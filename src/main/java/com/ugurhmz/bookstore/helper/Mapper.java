package com.ugurhmz.bookstore.helper;


import com.ugurhmz.bookstore.dto.responseDto.*;
import com.ugurhmz.bookstore.entities.*;

import java.util.*;
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

        List<String> bookNames = author.getBookList()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());
        authorResponseDto.setBookNames(bookNames);

        if (author.getPostCode() != null) {
            authorResponseDto.setPostCodeName(author.getPostCode().getName());
        }

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

    public static Map<String, List<String>> citiesToCityResDTO(List<City> cities) {
        List<String> cityNames = cities.stream()
                .map(City::getName)
                .collect(Collectors.toList());

        Map<String, List<String>> result = new HashMap<>();
        result.put("cityList", cityNames);

        return result;
    }

    public static PostCodeResponseDto postCodeToPostCodeResDTO(PostCode postCode){
        return new PostCodeResponseDto(postCode.getId(), postCode.getName(), postCode.getCity());
    }


}
