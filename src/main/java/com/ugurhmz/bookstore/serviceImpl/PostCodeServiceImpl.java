package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.PostCodeRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.PostCodeResponseDto;
import com.ugurhmz.bookstore.entities.City;
import com.ugurhmz.bookstore.entities.PostCode;
import com.ugurhmz.bookstore.helper.Mapper;
import com.ugurhmz.bookstore.repository.CityRepository;
import com.ugurhmz.bookstore.repository.PostCodeRepository;
import com.ugurhmz.bookstore.service.PostCodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostCodeServiceImpl implements PostCodeService {
    private final PostCodeRepository postCodeRepository;
    private final CityRepository cityRepository;

    @Autowired
    public PostCodeServiceImpl(PostCodeRepository postCodeRepository, CityRepository cityRepository) {
        this.postCodeRepository = postCodeRepository;
        this.cityRepository = cityRepository;
    }

    // CREATE
    @Transactional
    @Override
    public PostCodeResponseDto createPostCode(PostCodeRequestDto postCodeRequestDto) {
        PostCode postCode = new PostCode();
        String postCodeName = Optional.ofNullable(postCodeRequestDto.getName())
                .map(String::trim)
                .filter( name -> !name.isEmpty())
                .orElseThrow( () -> new IllegalArgumentException("Postcode name cannot be empty or null!!"));

        postCode.setName(postCodeName);

        // Set City to PostCode
        if(postCodeRequestDto.getCityId() != null){
            City thatCity = cityRepository.findById(postCodeRequestDto.getCityId())
                    .orElseThrow(() -> new IllegalArgumentException("City not found with city id" + postCodeRequestDto.getCityId()));
            postCode.setCity(thatCity);
        } else {
            throw new IllegalArgumentException("City ID cannot be empty or null");
        }

        PostCode savedPostCode = postCodeRepository.save(postCode);
        return Mapper.postCodeToPostCodeResDTO(savedPostCode);
    }


    @Override
    public PostCodeResponseDto getPostCode(Long postCodeId) {
       PostCode thatPostCode = postCodeRepository.findById(postCodeId)
               .orElseThrow(() -> new IllegalArgumentException("Postcode not found with id: "+ postCodeId));

        System.out.println("mypostcode: " + thatPostCode);
       return Mapper.postCodeToPostCodeResDTO(thatPostCode);
    }

    @Override
    public PostCodeRequestDto updatePostCode(Long postCodeId, PostCodeRequestDto postCodeRequestDto) {
        return null;
    }

    @Override
    public String deletePostCode(Long postCodeId) {
        return null;
    }
}
