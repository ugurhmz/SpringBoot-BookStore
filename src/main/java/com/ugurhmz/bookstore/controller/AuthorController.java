package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.AuthorRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.AuthorResponseDto;
import com.ugurhmz.bookstore.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create-author")
    public ResponseEntity<Map<String, Object>> createAuthor(@RequestBody final AuthorRequestDto authorRequestDto) {
        try {
           AuthorResponseDto authorResponseDto = authorService.createAuthor(authorRequestDto);
           return new ResponseEntity<>(Collections.singletonMap("message",authorResponseDto), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {
            String errMsg = "Error while creating the Author " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-all")
    public ResponseEntity<Map<String, Object>> getAllAuthors(){
        try {
           List<AuthorResponseDto> authorResponseDtoList= authorService.getAllAuthors();
           return new ResponseEntity<>(Collections.singletonMap("message", authorResponseDtoList), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
         catch (Exception e) {
            String errMsg = "Error while getting all authors " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", errMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
