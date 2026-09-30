package com.allanfelipe.agendaja.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;

import org.springframework.stereotype.Service;

import com.allanfelipe.agendaja.dto.DashboardResponseDTO;
import com.allanfelipe.agendaja.model.StatusAgendamento;
import com.allanfelipe.agendaja.model.StatusPagamento;
import com.allanfelipe.agendaja.repository.AgendamentoRepository;
import com.allanfelipe.agendaja.repository.PagamentoRepository;

@Service
public class DashboardService {

    private final PagamentoRepository pagamentoRepository;
    private final AgendamentoRepository agendamentoRepository;

    public DashboardService(PagamentoRepository pagamentoRepository, AgendamentoRepository agendamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public DashboardResponseDTO resumoDoMes(YearMonth mes) {
        LocalDateTime inicio = mes.atDay(1).atStartOfDay();
        LocalDateTime fim = mes.plusMonths(1).atDay(1).atStartOfDay();

        BigDecimal faturamento = pagamentoRepository.somarValorPorStatusEPeriodo(StatusPagamento.PAGO, inicio, fim);

        // agendamentos cancelados não entram no cálculo de inadimplência
        long total = agendamentoRepository.countByStatusNotAndDataHoraGreaterThanEqualAndDataHoraLessThan(
                StatusAgendamento.CANCELADO, inicio, fim);
        long naoPagos = agendamentoRepository.contarNaoPagos(
                StatusAgendamento.CANCELADO, StatusPagamento.PAGO, inicio, fim);

        BigDecimal taxa = total == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(naoPagos * 100).divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);

        return new DashboardResponseDTO(mes, faturamento, total, naoPagos, taxa);
    }
}
