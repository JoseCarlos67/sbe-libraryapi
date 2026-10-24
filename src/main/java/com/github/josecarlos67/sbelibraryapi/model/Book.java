package com.github.josecarlos67.sbelibraryapi.model;

import com.github.josecarlos67.sbelibraryapi.model.enums.BookGenre;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(length = 20, nullable = false)
  private String isbn;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(name = "date_publication")
  private LocalDate datePublication;

  @Enumerated(EnumType.STRING)
  @Column(length = 30, nullable = false)
  private BookGenre genre;

  @Column(precision = 18, scale = 2)
  private BigDecimal price;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_author")
  private Author idAuthor;

}
