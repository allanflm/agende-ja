package com.allanfelipe.agendaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanfelipe.agendaja.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
