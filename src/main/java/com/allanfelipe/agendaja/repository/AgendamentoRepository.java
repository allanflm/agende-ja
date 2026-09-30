package com.allanfelipe.agendaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanfelipe.agendaja.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
