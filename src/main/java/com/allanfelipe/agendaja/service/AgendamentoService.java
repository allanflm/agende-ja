package com.allanfelipe.agendaja.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanfelipe.agendaja.dto.AgendamentoRequestDTO;
import com.allanfelipe.agendaja.exception.BusinessException;
import com.allanfelipe.agendaja.exception.ResourceNotFoundException;
import com.allanfelipe.agendaja.model.Agendamento;
import com.allanfelipe.agendaja.model.Cliente;
import com.allanfelipe.agendaja.model.Servico;
import com.allanfelipe.agendaja.model.StatusAgendamento;
import com.allanfelipe.agendaja.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;
    private final ServicoService servicoService;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            ClienteService clienteService,
            ServicoService servicoService) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteService = clienteService;
        this.servicoService = servicoService;
    }

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com id " + id));
    }

    public Agendamento criar(AgendamentoRequestDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.clienteId());
        Servico servico = servicoService.buscarPorId(dto.servicoId());

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setDataHora(dto.dataHora());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento cancelar(Long id) {
        Agendamento agendamento = buscarPorId(id);
        if (agendamento.getStatus() != StatusAgendamento.AGENDADO) {
            throw new BusinessException(
                    "Somente agendamentos com status AGENDADO podem ser cancelados");
        }
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento concluir(Long id) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        return agendamentoRepository.save(agendamento);
    }
}
