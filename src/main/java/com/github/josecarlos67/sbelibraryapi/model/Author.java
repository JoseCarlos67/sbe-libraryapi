package com.github.josecarlos67.sbelibraryapi.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(length = 100)
  private String name;

  @Column(name = "date_birth")
  private LocalDate dataBirth;

  @Column(length = 50)
  private String nationality;

  @OneToMany(mappedBy = "idAuthor")
  private List<Book> bookList;

}
