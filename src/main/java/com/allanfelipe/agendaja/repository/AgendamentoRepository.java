package com.allanfelipe.agendaja.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.allanfelipe.agendaja.model.Agendamento;
import com.allanfelipe.agendaja.model.StatusAgendamento;
import com.allanfelipe.agendaja.model.StatusPagamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    long countByStatusNotAndDataHoraGreaterThanEqualAndDataHoraLessThan(StatusAgendamento status,
            LocalDateTime inicio, LocalDateTime fim);

    @Query("""
            select count(a) from Agendamento a
            where a.status <> :cancelado
              and a.dataHora >= :inicio and a.dataHora < :fim
              and not exists (select 1 from Pagamento p
                              where p.agendamento = a and p.statusPagamento = :pago)
            """)
    long contarNaoPagos(@Param("cancelado") StatusAgendamento cancelado, @Param("pago") StatusPagamento pago,
            @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
