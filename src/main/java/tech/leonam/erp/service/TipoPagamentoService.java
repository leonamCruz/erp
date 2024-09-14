package tech.leonam.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.repository.TipoPagamentoRepository;

@Slf4j
@Service
@AllArgsConstructor
public class TipoPagamentoService {
    private final TipoPagamentoRepository tipoPagamentoRepository;

    public TipoPagamento buscarTipoPagamentoPeloId(Long id) throws IdentificadorInvalidoException {
        log.info("Buscando tipo de pagamento com ID: {}", id);
        return tipoPagamentoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tipo de pagamento com ID: {} não foi encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do tipo de pagamento inválido");
                });
    }

    public Page<TipoPagamento> buscarTodosTiposPagamento(Integer pagina, Integer linhasPorPagina, String orderBy,
            String direcao) {
        log.info(
                "Buscando todos os tipos de pagamento - Página: {}, Linhas por página: {}, OrderBy: {}, Direção: {}",
                pagina, linhasPorPagina, orderBy, direcao);
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);
        return tipoPagamentoRepository.findAll(pageRequest);
    }

    @Transactional
    public Long salvarTipoPagamento(TipoPagamentoDTO tipoPagamentoDTO) {
        log.info("Salvando novo tipo de pagamento: {}", tipoPagamentoDTO);
        Long id = tipoPagamentoRepository.save(TipoPagamentoDTO.paraEntidade(tipoPagamentoDTO)).getId();
        log.info("Tipo de pagamento salvo com ID: {}", id);
        return id;
    }

    @Transactional
    public Long atualizarTipoPagamento(Long id, TipoPagamentoDTO tipoPagamentoDTO)
            throws IdentificadorInvalidoException {
        log.info("Atualizando tipo de pagamento com ID: {}", id);
        return tipoPagamentoRepository.findById(id)
                .map(m -> {
                    tipoPagamentoDTO.setId(m.getId());
                    Long updatedId = tipoPagamentoRepository.save(TipoPagamentoDTO.paraEntidade(tipoPagamentoDTO))
                            .getId();
                    log.info("Tipo de pagamento com ID: {} foi atualizado com sucesso", updatedId);
                    return updatedId;
                }).orElseThrow(() -> {
                    log.warn("Falha ao atualizar. Tipo de pagamento com ID: {} não encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do tipo de pagamento inválido");
                });
    }

    @Transactional
    public void deletarTipoPagamentoPeloId(Long id) {
        log.info("Deletando tipo de pagamento com ID: {}", id);
        if (tipoPagamentoRepository.existsById(id)) {
            tipoPagamentoRepository.deleteById(id);
            log.info("Tipo de pagamento com ID: {} deletado com sucesso", id);
        } else {
            log.warn("Falha ao deletar. Tipo de pagamento com ID: {} não encontrado", id);
        }
    }
}
