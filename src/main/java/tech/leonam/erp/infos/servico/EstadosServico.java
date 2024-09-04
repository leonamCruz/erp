package tech.leonam.erp.infos.servico;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.leonam.erp.infos.repositorio.EstadoRepository;

import java.util.Map;

@Service
@AllArgsConstructor
public class EstadosServico {
    private EstadoRepository repository;

    public Map<String,String> pegaTodosOsEstados() {
        return repository.findAll();
    }

}
