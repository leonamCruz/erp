package tech.leonam.erp.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tech.leonam.erp.repository.EstadoRepository;

@Service
@AllArgsConstructor
public class EstadosServico {
    private EstadoRepository repository;

    public Map<String,String> pegaTodosOsEstados() {
        return repository.findAll();
    }

}
