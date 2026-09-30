package com.allanfelipe.agendaja.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.allanfelipe.agendaja.dto.PagamentoRequestDTO;
import com.allanfelipe.agendaja.exception.BusinessException;
import com.allanfelipe.agendaja.exception.ResourceNotFoundException;
import com.allanfelipe.agendaja.model.Agendamento;
import com.allanfelipe.agendaja.model.Pagamento;
import com.allanfelipe.agendaja.model.StatusAgendamento;
import com.allanfelipe.agendaja.model.StatusPagamento;
import com.allanfelipe.agendaja.repository.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AgendamentoService agendamentoService;

    public PagamentoService(PagamentoRepository pagamentoRepository, AgendamentoService agendamentoService) {
        this.pagamentoRepository = pagamentoRepository;
        this.agendamentoService = agendamentoService;
    }

    public Pagamento buscarPorAgendamentoId(Long agendamentoId) {
        return pagamentoRepository.findByAgendamentoId(agendamentoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pagamento não encontrado para o agendamento " + agendamentoId));
    }

    public Pagamento marcarComoPago(Long agendamentoId, PagamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoService.buscarPorId(agendamentoId);

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new BusinessException("Não é possível registrar pagamento de um agendamento cancelado");
        }
        if (pagamentoRepository.findByAgendamentoId(agendamentoId).isPresent()) {
            throw new BusinessException("Este agendamento já possui pagamento registrado");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAgendamento(agendamento);
        pagamento.setValor(dto.valor());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setStatusPagamento(StatusPagamento.PAGO);
        pagamento.setDataPagamento(LocalDateTime.now());
        Pagamento salvo = pagamentoRepository.save(pagamento);

        agendamentoService.concluir(agendamentoId);

        return salvo;
    }
}
