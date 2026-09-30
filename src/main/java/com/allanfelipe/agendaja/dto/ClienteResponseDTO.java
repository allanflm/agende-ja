package com.allanfelipe.agendaja.dto;

import com.allanfelipe.agendaja.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email) {

    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }
}
