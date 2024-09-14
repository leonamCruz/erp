package tech.leonam.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.repository.ServicoRepository;

/*
 * Serviço para tratar as requisições do controller, este já é exclusivamente
 * da entidade de Servico.
 */

@Slf4j
@Service
@AllArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public Servico buscarServicosPeloId(Long id) throws IdentificadorInvalidoException {
        log.info("Buscando serviço com ID: {}", id);
        return servicoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Serviço com ID: {} não foi encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

    public Page<Servico> buscarTodosServicos(Integer pagina, Integer linhasPorPagina, String orderBy, String direcao) {
        log.info("Buscando todos os serviços - Página: {}, Linhas por página: {}, OrderBy: {}, Direção: {}",
                pagina, linhasPorPagina, orderBy, direcao);
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);
        return servicoRepository.findAll(pageRequest);
    }

    @Transactional
    public Long salvarServico(ServicoDTO servicoDTO) {
        log.info("Salvando novo serviço: {}", servicoDTO);
        Long id = servicoRepository.save(ServicoDTO.paraEntidade(servicoDTO)).getId();
        log.info("Serviço salvo com ID: {}", id);
        return id;
    }

    @Transactional
    public Long atualizarServico(Long id, ServicoDTO servicoDTO) throws IdentificadorInvalidoException {
        log.info("Atualizando serviço com ID: {}", id);
        return servicoRepository.findById(id)
                .map(m -> {
                    servicoDTO.setId(m.getId());
                    Long updatedId = servicoRepository.save(ServicoDTO.paraEntidade(servicoDTO)).getId();
                    log.info("Serviço com ID: {} foi atualizado com sucesso", updatedId);
                    return updatedId;
                }).orElseThrow(() -> {
                    log.warn("Falha ao atualizar. Serviço com ID: {} não encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

    @Transactional
    public void deletarServicoPeloId(Long id) {
        log.info("Deletando serviço com ID: {}", id);
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            log.info("Serviço com ID: {} deletado com sucesso", id);
        } else {
            log.warn("Falha ao deletar. Serviço com ID: {} não encontrado", id);
        }
    }

    public void alterarStatusDoServico(Long id, StatusServico status) throws IdentificadorInvalidoException {
        log.info("Atualizando status do serviço com ID: {}", id);
        servicoRepository.findById(id)
                .map(m -> {
                    m.setStatus(status);
                    servicoRepository.save(m);
                    log.info("Serviço com ID: {} foi atualizado com sucesso", status);
                    return Void.TYPE;
                })
                .orElseThrow(() -> {
                    log.warn("Falha ao atualizar. Serviço com ID: {} não encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

}
