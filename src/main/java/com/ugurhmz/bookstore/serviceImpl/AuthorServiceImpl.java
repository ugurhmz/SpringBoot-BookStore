package com.ugurhmz.bookstore.serviceImpl;

import com.ugurhmz.bookstore.dto.requestDto.AuthorRequestDto;
import com.ugurhmz.bookstore.dto.responseDto.AuthorResponseDto;
import com.ugurhmz.bookstore.entities.Author;
import com.ugurhmz.bookstore.entities.Book;
import com.ugurhmz.bookstore.entities.PostCode;
import com.ugurhmz.bookstore.helper.Mapper;
import com.ugurhmz.bookstore.repository.AuthorRepository;
import com.ugurhmz.bookstore.repository.BookRepository;
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
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, PostCodeRepository postCodeRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.postCodeRepository = postCodeRepository;
        this.bookRepository = bookRepository;
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

    // UPDATE
    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with ID: " + authorId));

        String updatedName = Optional.ofNullable(authorRequestDto.getName())
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Author name cannot be empty or null"));
        existingAuthor.setName(updatedName);


        Optional.ofNullable(authorRequestDto.getPostCode())
                .ifPresent(postCodeId -> {
                    Long postCodeIdValue = Optional.ofNullable(postCodeId)
                            .orElseThrow(() -> new IllegalArgumentException("Postcode ID cannot be empty"));
                    PostCode postCode = postCodeRepository.findById(postCodeIdValue)
                            .orElseThrow(() -> new IllegalArgumentException("Postcode not found with ID: " + postCodeIdValue));
                    existingAuthor.setPostCode(postCode);
        });

        Optional.ofNullable(authorRequestDto.getBookIds())
                .filter(bookIds -> !bookIds.isEmpty())
                .ifPresent(bookIds -> {
                    List<Book> updatedBooks = bookRepository.findAllById(bookIds);
                    existingAuthor.setBookList(updatedBooks);
        });
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return Mapper.authorToAuthorResDTO(updatedAuthor);
    }

    // DELETE
    @Transactional
    public String deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with ID: " + authorId));

        author.getBookList().forEach(book -> book.removeAuthor(author));

        author.setPostCode(null);
        authorRepository.delete(author);
        return "Author with ID: " + authorId + "has been deleted successfully.";
    }



}
