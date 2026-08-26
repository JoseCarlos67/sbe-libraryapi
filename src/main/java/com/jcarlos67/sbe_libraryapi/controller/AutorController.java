package com.jcarlos67.sbe_libraryapi.controller;

import com.jcarlos67.sbe_libraryapi.controller.dto.AutorDTO;
import com.jcarlos67.sbe_libraryapi.controller.dto.AutorRespostaDTO;
import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
public class AutorController {

  private final AutorService service;

  public AutorController(AutorService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
    Autor autor1 = autor.mapearParaAutor();
    service.salvar(autor1);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(autor1.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("{id}")
  public ResponseEntity<AutorRespostaDTO> obterDetalhes(@PathVariable("id") String id) {
    UUID idAutor = UUID.fromString(id);
    Optional<Autor> autorOptional = service.obterPorId(idAutor);
    if(autorOptional.isPresent()) {
      Autor autor = autorOptional.get();
      AutorRespostaDTO dto = new AutorRespostaDTO(
              autor.getId(),
              autor.getNome(),
              autor.getDataNascimento(),
              autor.getNacionalidade()
      );
      return ResponseEntity.ok(dto);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletar(@PathVariable String id) {
    UUID idAutor = UUID.fromString(id);
    Optional<Autor> autorOptional = service.obterPorId(idAutor);

    if(autorOptional.isPresent()) {
      service.deletar(autorOptional.get());
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<AutorRespostaDTO>> pesquisar(
          @RequestParam(value = "nome", required = false) String nome,
          @RequestParam(value = "nacionalidade", required = false) String nacionalidade
  ) {
    List<Autor> resultado = service.pesquisa(nome, nacionalidade);
    List<AutorRespostaDTO> lista = resultado
            .stream()
            .map(autor -> new AutorRespostaDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade()
            ))
            .collect(Collectors.toList());

            return ResponseEntity.ok(lista);
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> atualizar(
          @PathVariable UUID id,  @RequestBody AutorDTO dto) {
    Optional<Autor> autorOptional = service.obterPorId(id);

    if(autorOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Autor autor = autorOptional.get();
    autor.setNome(dto.nome());
    autor.setNacionalidade(dto.nacionalidade());
    autor.setDataNascimento(dto.dataNascimento());

    service.atualizar(autor);

    return ResponseEntity.noContent().build();
  }

}
