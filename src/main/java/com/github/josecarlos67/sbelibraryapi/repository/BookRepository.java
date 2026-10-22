package com.github.josecarlos67.sbelibraryapi.repository;

import com.github.josecarlos67.sbelibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
