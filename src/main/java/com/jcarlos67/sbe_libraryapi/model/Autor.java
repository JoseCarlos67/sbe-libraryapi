package com.jcarlos67.sbe_libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;

  @Column(name = "nacionalidade", length = 50, nullable = false)
  private String nacionalidade;

//  @OneToMany(mappedBy = "autor")
  @Transient
  private List<Livro> livros;

}
