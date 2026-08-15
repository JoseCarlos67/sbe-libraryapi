package com.jcarlos67.sbe_libraryapi.repository;

import com.jcarlos67.sbe_libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
