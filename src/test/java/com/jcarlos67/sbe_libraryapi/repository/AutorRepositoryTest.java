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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class AutorRepositoryTest {

  @Autowired
  AutorRepository repository;

  @Autowired
  LivroRepository livroRepository;

  @Test
  public void salvarTest() {
    Autor autor = new Autor(null, "José", LocalDate.of(1950, 10, 02),"Brasileiro", null);

    var autorSalvo = repository.save(autor);
    System.out.println("Autor salvo: " + autorSalvo);
  }

  @Test
  public void atualizarTest() {
    var id = UUID.fromString("4bdb98c3-41a5-4608-9f93-f9d003424462");

    Optional<Autor> possivelAutor = repository.findById(id);

    if(possivelAutor.isPresent()) {
      Autor autorEncontrado = possivelAutor.get();
      System.out.println("Dados do Autor:");
      System.out.println(autorEncontrado);

      autorEncontrado.setDataNascimento(LocalDate.of(1960, 2, 5));

      repository.save(autorEncontrado);
    }
  }

  @Test
  public void listarTodosTest() {
    List<Autor> lista = repository.findAll();
    lista.forEach(System.out::println);
  }

  @Test
  public void contarTotalAutoresTest() {
    System.out.println("Total de autores salvos: " + repository.count());
  }


  @Test
  public void deletarAutorPorIdTest() {
    var id = UUID.fromString("66783692-d6f4-4265-bb8e-eee42e8b648d");
    repository.deleteById(id);
  }


  @Test
  void salvarAutorComLivrosTest() {
    Autor autor = new Autor(null, "Larissa", LocalDate.of(1950, 10, 02),"Brasileira", null);

    Livro livro = new Livro (null, "90857-84874", "Hobbit", LocalDate.of(1980, 1, 2), GeneroLivro.FANTASIA, BigDecimal.valueOf(100), autor);

    Livro livro1 = new Livro (null, "90757-84874", "Senhor dos Aneis", LocalDate.of(1980, 1, 2), GeneroLivro.FANTASIA, BigDecimal.valueOf(100), autor);

    autor.setLivros(new ArrayList<>());
    autor.getLivros().add(livro);
    autor.getLivros().add(livro1);

    repository.save(autor);
    livroRepository.saveAll(autor.getLivros());
  }


  @Test
  void listarLivrosAutor() {
    UUID id = UUID.fromString("d8cbed25-a71b-45cc-ab1a-29e011137b93");
    Autor autor = repository.findById(id).get();

    List<Livro> livros = livroRepository.findByAutor(autor);
    autor.setLivros(livros);

    autor.getLivros().forEach(System.out::println);
  }

}
