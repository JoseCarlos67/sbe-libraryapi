package com.github.josecarlos67.sbelibraryapi.repository;

import com.github.josecarlos67.sbelibraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

  @Autowired
  AuthorRepository authorRepository;

  @Test
  public void saveTest() {
    Author author = new Author();
    author.setName("José");
    author.setNationality("Brasileiro");
    author.setDateBirth(LocalDate.of(1950, 1, 10));

    Author savedAuthor = authorRepository.save(author);
    System.out.println("Saved author: " + savedAuthor);
  }

  @Test
  public void updateTest() {
    UUID id = UUID.fromString("b8d829b7-7458-41bd-8bc5-e32fa5d4a961");

    Optional<Author> optionalAuthor = authorRepository.findById(id);

    if (optionalAuthor.isPresent()) {
      Author author = optionalAuthor.get();
      System.out.println("Author's data:");
      System.out.println(author);

      author.setDateBirth(LocalDate.of(1960, 1, 10));
      authorRepository.save(author);
    }
  }

}
