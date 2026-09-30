package com.allanfelipe.agendaja.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.allanfelipe.agendaja.model.Pagamento;
import com.allanfelipe.agendaja.model.StatusPagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByAgendamentoId(Long agendamentoId);

    @Query("""
            select coalesce(sum(p.valor), 0) from Pagamento p
            where p.statusPagamento = :status
              and p.dataPagamento >= :inicio and p.dataPagamento < :fim
            """)
    BigDecimal somarValorPorStatusEPeriodo(@Param("status") StatusPagamento status,
            @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
