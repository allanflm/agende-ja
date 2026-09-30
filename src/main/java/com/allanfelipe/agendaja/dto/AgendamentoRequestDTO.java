package com.allanfelipe.agendaja.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record AgendamentoRequestDTO(
        @NotNull(message = "Cliente é obrigatório") Long clienteId,
        @NotNull(message = "Serviço é obrigatório") Long servicoId,
        @NotNull(message = "Data e hora são obrigatórias") LocalDateTime dataHora) {
}
