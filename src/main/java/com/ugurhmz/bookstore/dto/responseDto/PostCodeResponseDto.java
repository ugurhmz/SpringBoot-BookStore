package com.ugurhmz.bookstore.dto.responseDto;


import com.ugurhmz.bookstore.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeResponseDto {
    private Long id;
    private String name;
    private City city;
}
