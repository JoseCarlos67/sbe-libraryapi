package com.jcarlos67.sbe_libraryapi.repository;

import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.model.Livro;
import com.jcarlos67.sbe_libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class LivroRepositoryTest {

  @Autowired
  LivroRepository livroRepository;

  @Autowired
  AutorRepository autorRepository;

  @Test
  void salvarLivroTest() {
    Autor autor = autorRepository.findById(UUID.fromString("07b4cfc2-408a-43f0-b1af-db45bc5016f5")).orElse(null);

    Livro livro = new Livro (null, "90887-84874", "1984", LocalDate.of(1980, 1, 2), GeneroLivro.FICCAO, BigDecimal.valueOf(100), autor);
    livroRepository.save(livro);
  }

}