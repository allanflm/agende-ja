package com.allanfelipe.agendaja.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanfelipe.agendaja.dto.AgendamentoRequestDTO;
import com.allanfelipe.agendaja.dto.AgendamentoResponseDTO;
import com.allanfelipe.agendaja.dto.PagamentoRequestDTO;
import com.allanfelipe.agendaja.dto.PagamentoResponseDTO;
import com.allanfelipe.agendaja.model.Agendamento;
import com.allanfelipe.agendaja.service.AgendamentoService;
import com.allanfelipe.agendaja.service.PagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final PagamentoService pagamentoService;

    public AgendamentoController(AgendamentoService agendamentoService, PagamentoService pagamentoService) {
        this.agendamentoService = agendamentoService;
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<AgendamentoResponseDTO> listar() {
        return agendamentoService.listar().stream().map(AgendamentoResponseDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public AgendamentoResponseDTO buscarPorId(@PathVariable Long id) {
        return AgendamentoResponseDTO.fromEntity(agendamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(@Valid @RequestBody AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoService.criar(dto);
        return ResponseEntity.created(URI.create("/api/agendamentos/" + agendamento.getId()))
                .body(AgendamentoResponseDTO.fromEntity(agendamento));
    }

    @PostMapping("/{id}/cancelar")
    public AgendamentoResponseDTO cancelar(@PathVariable Long id) {
        return AgendamentoResponseDTO.fromEntity(agendamentoService.cancelar(id));
    }

    @PostMapping("/{id}/pagamento")
    public ResponseEntity<PagamentoResponseDTO> marcarComoPago(
            @PathVariable Long id, @Valid @RequestBody PagamentoRequestDTO dto) {
        PagamentoResponseDTO response = PagamentoResponseDTO.fromEntity(
                pagamentoService.marcarComoPago(id, dto));
        return ResponseEntity.created(URI.create("/api/agendamentos/" + id + "/pagamento")).body(response);
    }

    @GetMapping("/{id}/pagamento")
    public PagamentoResponseDTO buscarPagamento(@PathVariable Long id) {
        return PagamentoResponseDTO.fromEntity(pagamentoService.buscarPorAgendamentoId(id));
    }
}
