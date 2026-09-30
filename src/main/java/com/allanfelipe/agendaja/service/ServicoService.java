package com.allanfelipe.agendaja.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanfelipe.agendaja.dto.ServicoRequestDTO;
import com.allanfelipe.agendaja.exception.ResourceNotFoundException;
import com.allanfelipe.agendaja.model.Servico;
import com.allanfelipe.agendaja.repository.ServicoRepository;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com id " + id));
    }

    public Servico criar(ServicoRequestDTO dto) {
        Servico servico = new Servico();
        servico.setNome(dto.nome());
        servico.setPreco(dto.preco());
        servico.setDuracaoMinutos(dto.duracaoMinutos());
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, ServicoRequestDTO dto) {
        Servico servico = buscarPorId(id);
        servico.setNome(dto.nome());
        servico.setPreco(dto.preco());
        servico.setDuracaoMinutos(dto.duracaoMinutos());
        return servicoRepository.save(servico);
    }

    public void deletar(Long id) {
        Servico servico = buscarPorId(id);
        servicoRepository.delete(servico);
    }
}
