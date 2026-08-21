package com.jcarlos67.sbe_libraryapi.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AutorRespostaDTO(
        UUID uuid,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {
}
