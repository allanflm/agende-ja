package com.allanfelipe.agendaja.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.allanfelipe.agendaja.model.Pagamento;
import com.allanfelipe.agendaja.model.StatusPagamento;

public record PagamentoResponseDTO(
        Long id,
        Long agendamentoId,
        BigDecimal valor,
        String formaPagamento,
        StatusPagamento statusPagamento,
        LocalDateTime dataPagamento) {

    public static PagamentoResponseDTO fromEntity(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getAgendamento().getId(),
                pagamento.getValor(),
                pagamento.getFormaPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getDataPagamento());
    }
}
