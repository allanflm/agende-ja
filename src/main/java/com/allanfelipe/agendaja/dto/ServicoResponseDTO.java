package com.allanfelipe.agendaja.dto;

import java.math.BigDecimal;

import com.allanfelipe.agendaja.model.Servico;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer duracaoMinutos) {

    public static ServicoResponseDTO fromEntity(Servico servico) {
        return new ServicoResponseDTO(
                servico.getId(), servico.getNome(), servico.getPreco(), servico.getDuracaoMinutos());
    }
}
