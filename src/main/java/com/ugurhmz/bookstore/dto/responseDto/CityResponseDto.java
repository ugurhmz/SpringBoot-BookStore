package com.ugurhmz.bookstore.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class CityResponseDto {
    public List<String> namesCity;

    public CityResponseDto(List<String> names) {
        this.namesCity = names;
    }
}
