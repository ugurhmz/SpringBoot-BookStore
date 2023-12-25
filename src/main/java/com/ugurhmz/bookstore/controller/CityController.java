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

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<City> createCity(@RequestBody final CityRequestDto cityRequestDto) {
        City city = cityService.createCity(cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
