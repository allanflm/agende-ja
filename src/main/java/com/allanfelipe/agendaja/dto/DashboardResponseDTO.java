package com.allanfelipe.agendaja.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record DashboardResponseDTO(
        YearMonth mes,
        BigDecimal faturamento,
        long totalAgendamentos,
        long agendamentosNaoPagos,
        BigDecimal taxaInadimplencia) {
}
