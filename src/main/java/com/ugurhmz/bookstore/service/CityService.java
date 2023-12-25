package com.ugurhmz.bookstore.service;

import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.entities.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public City createCity(CityRequestDto cityRequestDto);
    public List<City> getAllCities();
    public City getOneCity(Long cityId);
    public City updateCity(Long cityId, CityRequestDto cityRequestDto);
    public City deleteCity(Long cityId);
}
