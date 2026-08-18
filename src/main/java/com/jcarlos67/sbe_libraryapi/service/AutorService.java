package com.jcarlos67.sbe_libraryapi.service;

import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

  private final AutorRepository repository;

  public AutorService(AutorRepository repository) {
    this.repository = repository;
  }

  public Autor salvar(Autor autor) {
    return repository.save(autor);
  }

}
