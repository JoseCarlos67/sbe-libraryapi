package com.jcarlos67.sbe_libraryapi.controller;

import com.jcarlos67.sbe_libraryapi.controller.dto.AutorDTO;
import com.jcarlos67.sbe_libraryapi.controller.dto.AutorRespostaDTO;
import com.jcarlos67.sbe_libraryapi.model.Autor;
import com.jcarlos67.sbe_libraryapi.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
@Tag(name = "Autores", description = "Operations related to author management")
public class AutorController {

  private final AutorService service;

  public AutorController(AutorService service) {
    this.service = service;
  }

  @Operation(
          summary = "Create a new author",
          description = "Registers a new author and returns its URI location in the Header"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Author created successfully", headers = @Header(name = "Location", description = "URI to retrieve the created author"), content = @Content),
          @ApiResponse(responseCode = "422", description = "Validation error", content = @Content),
          @ApiResponse(responseCode = "409", description = "Duplicate record", content = @Content)
  })
  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
    Autor autor1 = autor.mapearParaAutor();
    service.salvar(autor1);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(autor1.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @Operation(
          summary = "Search for authors",
          description = "Returns a list of authors filtered by name and nationality"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Authors found", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AutorRespostaDTO.class)))
          )
  })
  @GetMapping
  public ResponseEntity<List<AutorRespostaDTO>> pesquisar(
          @Parameter(description = "Filter by author name") @RequestParam(value = "nome", required = false) String nome,
          @Parameter(description = "Filter by author nationality") @RequestParam(value = "nacionalidade", required = false) String nacionalidade
  ) {
    List<Autor> resultado = service.pesquisa(nome, nacionalidade);
    List<AutorRespostaDTO> lista = resultado
            .stream()
            .map(autor -> new AutorRespostaDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade()
            ))
            .collect(Collectors.toList());

    return ResponseEntity.ok(lista);
  }

  @Operation(
          summary = "Get author details",
          description = "Retrieve one author by their UUID"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Author found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorRespostaDTO.class))),
          @ApiResponse(responseCode = "404", description = "Author not found", content = @Content)
  })
  @GetMapping("{id}")
  public ResponseEntity<AutorRespostaDTO> obterDetalhes(
          @Parameter(description = "Author UUID", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
          @PathVariable UUID id
  ) {
    Optional<Autor> autorOptional = service.obterPorId(id);
    if (autorOptional.isPresent()) {
      Autor autor = autorOptional.get();
      AutorRespostaDTO dto = new AutorRespostaDTO(
              autor.getId(),
              autor.getNome(),
              autor.getDataNascimento(),
              autor.getNacionalidade()
      );
      return ResponseEntity.ok(dto);
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(
          summary = "Delete an author",
          description = "Delete an author by their UUID"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Author successfully deleted", content = @Content),
          @ApiResponse(responseCode = "404", description = "Author not found", content = @Content)
  })
  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletar(
          @Parameter(description = "Author UUID", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
          @PathVariable UUID id
  ) {
    Optional<Autor> autorOptional = service.obterPorId(id);

    if (autorOptional.isPresent()) {
      service.deletar(autorOptional.get());
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
  }

  @Operation(
          summary = "Update an author's data",
          description = "Update an author's data by their UUID"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Author updated successfully", content = @Content),
          @ApiResponse(responseCode = "404", description = "Author not found", content = @Content),
          @ApiResponse(responseCode = "422", description = "Validation error", content = @Content)
  })
  @PutMapping("{id}")
  public ResponseEntity<Void> atualizar(
          @Parameter(description = "Author UUID", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
          @PathVariable UUID id,
          @RequestBody AutorDTO dto
  ) {
    Optional<Autor> autorOptional = service.obterPorId(id);

    if (autorOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Autor autor = autorOptional.get();
    autor.setNome(dto.nome());
    autor.setNacionalidade(dto.nacionalidade());
    autor.setDataNascimento(dto.dataNascimento());

    service.atualizar(autor);

    return ResponseEntity.noContent().build();
  }
}