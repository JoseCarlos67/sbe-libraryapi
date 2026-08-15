package com.jcarlos67.sbe_libraryapi.model;

import com.jcarlos67.sbe_libraryapi.model.enums.GeneroLivro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Getter(AccessLevel.NONE)
  private UUID id;

  @Column(name = "isbn", length = 20, nullable = false, unique = true)
  private String isbn;

  @Column(name = "titulo", length = 150, nullable = false)
  private String titulo;

  @Column(name = "data_publicasao", nullable = false)
  private String dataPublicacao;

  @Enumerated(EnumType.STRING)
  @Column(name = "genero", length = 30, nullable = false)
  private GeneroLivro genero;

  @Column(name = "preco", nullable = false, precision = 18, scale = 2)
  private BigDecimal preco;

  @ManyToOne
  @JoinColumn(name = "id_autor")
  private Autor autor;

}
