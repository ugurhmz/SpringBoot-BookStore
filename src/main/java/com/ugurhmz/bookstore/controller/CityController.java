package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CityResponseDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createCity(@RequestBody final CityRequestDto cityRequestDto) {
        return Optional.ofNullable(cityRequestDto)
                .map(dto -> dto.getName())
                .filter(name -> !name.trim().isEmpty())
                .map(name -> {
                    try {
                        City city = cityService.createCity(cityRequestDto);
                        String message = city.getName();
                        return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
                    } catch (Exception e) {
                        String errorMessage = "Error while adding city: " + e.getMessage();
                        return new ResponseEntity<>(Collections.singletonMap("error", errorMessage), HttpStatus.BAD_REQUEST);
                    }
                })
                .orElse(new ResponseEntity<>(Collections.singletonMap("error", "City name cannot be empty or null"), HttpStatus.BAD_REQUEST));
    }


    // GET ALL
    @GetMapping("/all-cities")
    public ResponseEntity<?> getAllCities() {
        try {
            List<City> cities = cityService.getAllCities();
            List<String> cityNames = cities.stream()
                    .map(City::getName)
                    .collect(Collectors.toList());

            CityResponseDto cityResponse = new CityResponseDto(cityNames);

            return cities.isEmpty()
                    ? new ResponseEntity<>(Collections.singletonMap("error", "No cities found!"), HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(cityResponse, HttpStatus.OK);

        } catch (Exception e) {
            String errorMessage = "Error while retrieving cities: " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getOneCityWithId(@PathVariable final Long id) {
        try {
            City city = cityService.getOneCity(id);
            String message = city.getName();
            return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error while retrieving city: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
