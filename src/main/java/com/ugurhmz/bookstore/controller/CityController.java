package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCity(@RequestBody final CityRequestDto cityRequestDto) {
        return Optional.ofNullable(cityRequestDto)
                .map(dto -> dto.getName())
                .filter(name -> !name.trim().isEmpty())
                .map(name -> {
                    try {
                        City city = cityService.createCity(cityRequestDto);
                        return new ResponseEntity<>(city, HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>("Error while adding city: " + e.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                })
                .orElse(new ResponseEntity<>("City name cannot be empty or null", HttpStatus.BAD_REQUEST));
    }

}
