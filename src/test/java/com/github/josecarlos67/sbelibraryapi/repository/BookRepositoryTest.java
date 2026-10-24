package com.github.josecarlos67.sbelibraryapi.repository;

import com.github.josecarlos67.sbelibraryapi.model.Author;
import com.github.josecarlos67.sbelibraryapi.model.Book;
import com.github.josecarlos67.sbelibraryapi.model.enums.BookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Test
  void saveTest() {
    Book book = new Book();
    book.setIsbn("29292-292299");
    book.setPrice(BigDecimal.valueOf(100));
    book.setGenre(BookGenre.FICCAO);
    book.setTitle("UFO");
    book.setDatePublication(LocalDate.of(1990, 1, 2));

    Author author = authorRepository
            .findById(UUID.fromString("3ee8b0cb-714f-45f9-a429-32501dbce98b"))
            .orElse(null);

    book.setIdAuthor(author);

    bookRepository.save(book);
  }

}