package com.allanfelipe.agendaja.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanfelipe.agendaja.dto.ServicoRequestDTO;
import com.allanfelipe.agendaja.dto.ServicoResponseDTO;
import com.allanfelipe.agendaja.model.Servico;
import com.allanfelipe.agendaja.service.ServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<ServicoResponseDTO> listar() {
        return servicoService.listar().stream().map(ServicoResponseDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ServicoResponseDTO buscarPorId(@PathVariable Long id) {
        return ServicoResponseDTO.fromEntity(servicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@Valid @RequestBody ServicoRequestDTO dto) {
        Servico servico = servicoService.criar(dto);
        return ResponseEntity.created(URI.create("/api/servicos/" + servico.getId()))
                .body(ServicoResponseDTO.fromEntity(servico));
    }

    @PutMapping("/{id}")
    public ServicoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ServicoRequestDTO dto) {
        return ServicoResponseDTO.fromEntity(servicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
