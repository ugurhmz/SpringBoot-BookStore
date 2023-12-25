package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.CityRequestDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.repository.CityRepository;
import com.ugurhmz.bookstore.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImply implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImply(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    // CREATE
    @Override
    public City createCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        System.out.println("ServiceIMPL CREATE WORKED.");
        return cityRepository.save(city);
    }

    // GET ALL CITIES
    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    // GET CITY
    @Override
    public City getOneCity(Long cityId) {
       return cityRepository.findById(cityId).orElseThrow(
               () -> new IllegalArgumentException(cityId + " : City not  found!!"));
    }

    // UPDATE CITY
    @Override
    public City updateCity(Long cityId, CityRequestDto cityRequestDto) {
        return null;
    }

    // DELETE CITY
    @Override
    public City deleteCity(Long cityId) {
        City deleteToCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + cityId));

        cityRepository.delete(deleteToCity);
        return deleteToCity;
    }
}
