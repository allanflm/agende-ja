package com.allanfelipe.agendaja.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        String telefone,
        @Email(message = "Email inválido") String email) {
}
