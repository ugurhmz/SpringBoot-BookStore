package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.AuthorRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.AuthorResponseDto;
import com.ugurhmz.bookstore.entities.Author;
import com.ugurhmz.bookstore.entities.PostCode;
import com.ugurhmz.bookstore.helper.Mapper;
import com.ugurhmz.bookstore.repository.AuthorRepository;
import com.ugurhmz.bookstore.repository.PostCodeRepository;
import com.ugurhmz.bookstore.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final PostCodeRepository postCodeRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, PostCodeRepository postCodeRepository) {
        this.authorRepository = authorRepository;
        this.postCodeRepository = postCodeRepository;
    }

    // CREATE
    @Transactional
    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        String authorName = Optional.ofNullable(authorRequestDto.getName())
                .map(String::trim)
                .filter( name -> !name.isEmpty())
                .orElseThrow( () -> new IllegalArgumentException("Author name cannot be empty!!"));

        author.setName(authorName);
        System.out.println("kod"+authorRequestDto.getPostCode());
        Long postID = Optional.ofNullable(authorRequestDto.getPostCode())
                .orElseThrow(() -> new IllegalArgumentException("Postcode ID cannot be empty !!"));

        PostCode foundPostCode = postCodeRepository.findById(postID)
               .orElseThrow(() -> new IllegalArgumentException("Postcode not found with ID: " + postID));
       author.setPostCode(foundPostCode);
       Author savedAuthor = authorRepository.save(author);
       AuthorResponseDto authorResponseDto = Mapper.authorToAuthorResDTO(savedAuthor);

       return authorResponseDto;
    }

    // GET ALL AUTHORS
    @Override
    public List<AuthorResponseDto> getAllAuthors() {
      List<Author> authorList = authorRepository.findAll();
      return Optional.ofNullable(authorList)
              .filter( author -> !author.isEmpty())
              .map(Mapper::authorToAuthorResDTOS)
              .orElseThrow( () -> new IllegalArgumentException("Authors list empty!"));
    }
}
