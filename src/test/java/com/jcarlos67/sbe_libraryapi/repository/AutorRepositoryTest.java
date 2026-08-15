package com.jcarlos67.sbe_libraryapi.repository;

import com.jcarlos67.sbe_libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class AutorRepositoryTest {

  @Autowired
  AutorRepository repository;

  @Test
  public void salvarTest() {
    Autor autor = new Autor(null, "José", LocalDate.of(1950, 10, 02),"Brasileiro", null);

    var autorSalvo = repository.save(autor);
    System.out.println("Autor savl: " + autorSalvo);
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

}
