package com.allanfelipe.agendaja.dto;

import java.time.LocalDateTime;

import com.allanfelipe.agendaja.model.Agendamento;
import com.allanfelipe.agendaja.model.StatusAgendamento;

public record AgendamentoResponseDTO(
        Long id,
        ClienteResponseDTO cliente,
        ServicoResponseDTO servico,
        LocalDateTime dataHora,
        StatusAgendamento status) {

    public static AgendamentoResponseDTO fromEntity(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                ClienteResponseDTO.fromEntity(agendamento.getCliente()),
                ServicoResponseDTO.fromEntity(agendamento.getServico()),
                agendamento.getDataHora(),
                agendamento.getStatus());
    }
}
