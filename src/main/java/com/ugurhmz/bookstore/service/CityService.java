package com.ugurhmz.bookstore.service;

import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CityResponseDto;
import com.ugurhmz.bookstore.entities.City;
import java.util.List;

public interface CityService {
    CityResponseDto createCity(CityRequestDto cityRequestDto);
    List<String> getAllCities();
    CityResponseDto getOneCity(Long cityId);
    CityResponseDto updateCity(Long cityId, CityRequestDto cityRequestDto);
    CityResponseDto deleteCity(Long cityId);
}
