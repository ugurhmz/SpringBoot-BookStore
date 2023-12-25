package com.ugurhmz.bookstore.controller;


import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CityResponseDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.helper.Mapper;
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
    public ResponseEntity<Map<String, Object>> getAllCities() {
        try {
            List<City> cities = cityService.getAllCities();
            CityResponseDto cityResponse = Mapper.citiesToCityResDTO(cities);
            System.out.println("cityResponse: "+cityResponse);
            return cities.isEmpty()
                    ? new ResponseEntity<>(Collections.singletonMap("error", "No cities found!"), HttpStatus.NOT_FOUND)
                    : new ResponseEntity<>(Collections.singletonMap("cityList", cityResponse), HttpStatus.OK);

        } catch (Exception e) {
            String errorMessage = "Error while retrieving cities: " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOneCityWithId(@PathVariable final Long id) {
      try {
          City city = cityService.getOneCity(id);
          CityResponseDto cityResponseDto = Mapper.cityToCityResDTO(city);

          Map<String, Object> responseData = new HashMap<>();
          responseData.put("data", cityResponseDto.getNamesCity().get(0));

          return new ResponseEntity<>(responseData, HttpStatus.OK);
      } catch (IllegalArgumentException e) {
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("error", e.getMessage());
          return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
      } catch (Exception e) {
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("error", "Error while retrieving city: " + e.getMessage());
          return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    // DELETE CITY
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCity(@PathVariable final Long id) {
        try {
          City deletedCity = cityService.deleteCity(id);
          String msg = "Deletion successful, deleted city name: "+ deletedCity.getName() + ", id: " + deletedCity.getId();
          return new ResponseEntity<>(Collections.singletonMap("message", msg), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error",e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String myErrorMessage = "Error while deleting city: " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", myErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateCity(
            @PathVariable final Long id,
            @RequestBody CityRequestDto cityRequestDto) {

        if (cityRequestDto == null || cityRequestDto.getName() == null || cityRequestDto.getName().trim().isEmpty()) {
            String myMsg = "Cannot be null or empty !!";
            return new ResponseEntity<>(Collections.singletonMap("error", myMsg), HttpStatus.BAD_REQUEST);
        }

        try {
            City updatedCity = cityService.updateCity(id, cityRequestDto);
            String message = "Update successful, updated city name: " + updatedCity.getName() + ", id: " + updatedCity.getId();
            return new ResponseEntity<>(Collections.singletonMap("message", message), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String myErrorMessage = "Error while updating city: " + e.getMessage();
            return new ResponseEntity<>(Collections.singletonMap("error", myErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}