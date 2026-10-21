package com.github.josecarlos67.sbelibraryapi.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Author {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  @Setter(AccessLevel.NONE)
  private UUID id;

  private String name;

  @Column(name = "date_birth")
  private LocalDate dataBirth;

  private String nationality;

}
