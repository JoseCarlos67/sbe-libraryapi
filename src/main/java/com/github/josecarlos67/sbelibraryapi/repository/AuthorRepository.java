package com.github.josecarlos67.sbelibraryapi.repository;

import com.github.josecarlos67.sbelibraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
