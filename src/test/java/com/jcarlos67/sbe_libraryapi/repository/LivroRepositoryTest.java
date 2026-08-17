package com.jcarlos67.sbe_libraryapi.repository;

import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.model.Livro;
import com.jcarlos67.sbe_libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

  @Test
  void atualizarAutorLivroTest() {
    UUID id = UUID.fromString("439264cf-c54a-47cf-860f-d079f03d68f3");
    Livro livroParaAtualizar = livroRepository.findById(id).orElse(null);

    UUID idAutor = UUID.fromString("4bdb98c3-41a5-4608-9f93-f9d003424462");
    Autor carlos = autorRepository.findById(idAutor).orElse(null);

    livroParaAtualizar.setAutor(carlos);

    livroRepository.save(livroParaAtualizar);
  }

  @Test
  void deletarLivroPorIdTest() {
    UUID id = UUID.fromString("439264cf-c54a-47cf-860f-d079f03d68f3");
    livroRepository.deleteById(id);
  }

  @Test
  @Transactional
  void buscarLivroTest() {
    UUID idLivro = UUID.fromString("d5a4cbbd-8a18-4427-a297-270ff03510ba");
    Livro livro = livroRepository.findById(idLivro).orElse(null);
    System.out.println(livro.getTitulo());
    System.out.println(livro.getAutor().getNome());
  }

  @Test
  void pesquisarPorTituloTest() {
    List<Livro> livros = livroRepository.findByTitulo("Hobbit");
    livros.forEach(System.out::println);
  }
}