package com.jcarlos67.sbe_libraryapi.repository;

import com.jcarlos67.sbe_libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
