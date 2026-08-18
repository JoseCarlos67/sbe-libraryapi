package com.jcarlos67.sbe_libraryapi.controller;

import com.jcarlos67.sbe_libraryapi.controller.dto.AutorDTO;
import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

}
