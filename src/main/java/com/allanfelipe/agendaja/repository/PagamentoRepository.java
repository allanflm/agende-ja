package com.allanfelipe.agendaja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanfelipe.agendaja.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByAgendamentoId(Long agendamentoId);
}
