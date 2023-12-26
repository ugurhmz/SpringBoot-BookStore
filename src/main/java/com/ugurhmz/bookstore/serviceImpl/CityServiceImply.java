package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.CityResponseDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.helper.Mapper;
import com.ugurhmz.bookstore.repository.CityRepository;
import com.ugurhmz.bookstore.service.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImply implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImply(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // CREATE
    @Override
    @Transactional
    public CityResponseDto createCity(CityRequestDto cityRequestDto) {
        String cityName = Optional.ofNullable(cityRequestDto.getName())
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("City name can not be empty or null"));

        System.out.println("cityName"+cityName);
        City city = new City();
        city.setName(cityName);

        City createdCity = cityRepository.save(city);
        return Mapper.cityToCityResDTO(createdCity);
    }

    // GET ALL
    @Override
    public List<String> getAllCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);

        // Boş , null  filter
        return cities.stream()
                .map(City::getName)
                .filter(name -> name != null && !name.trim().isEmpty())
                .collect(Collectors.toList());
    }

    // GET ONE
    @Override
    public CityResponseDto getOneCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException(cityId + " : City not found!!"));
        return Mapper.cityToCityResDTO(city);
    }

    // UPDATE
    @Override
    @Transactional
    public CityResponseDto updateCity(Long cityId, CityRequestDto cityRequestDto) {
        String cityName = Optional.ofNullable(cityRequestDto.getName())
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("City name can not be empty or null!!"));
        City existingCity = cityRepository.findById(cityId)
                .orElseThrow( () -> new IllegalArgumentException("City not found with id:  "+ cityId));

        existingCity.setName(cityName);
        return Mapper.cityToCityResDTO(existingCity);
    }

    // DELETE
    @Override
    @Transactional
    public CityResponseDto deleteCity(Long cityId) {
        City deleteToCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + cityId));

        cityRepository.delete(deleteToCity);
        return Mapper.cityToCityResDTO(deleteToCity);
    }
}
