package com.allanfelipe.agendaja.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServicoRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotNull(message = "Preço é obrigatório") @Positive(message = "Preço deve ser positivo") BigDecimal preco,
        @NotNull(message = "Duração é obrigatória") @Positive(message = "Duração deve ser positiva") Integer duracaoMinutos) {
}
