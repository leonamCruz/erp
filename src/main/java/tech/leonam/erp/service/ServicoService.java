package tech.leonam.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.leonam.erp.exceptions.DataPagamentoPrevistoException;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.entity.TipoPagamento;
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
    private final TipoPagamentoService tipoPagamentoService;

    public Servico buscarServicosPeloId(Long id) throws IdentificadorInvalidoException {
        log.info("Buscando serviço com ID: {}", id);
        return servicoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Serviço com ID: {} não foi encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

    public Page<Servico> buscarTodosServicos(Integer pagina, Integer linhasPorPagina, String orderBy, String direcao,
            Integer status) {
        log.info("Buscando todos os serviços - Página: {}, Linhas por página: {}, OrderBy: {}, Direção: {}",
                pagina, linhasPorPagina, orderBy, direcao);
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);
        return servicoRepository.findAllByStatus(StatusServico.fromCodigo(status), pageRequest);
    }

    @Transactional
    public Servico salvarServico(ServicoDTO servicoDTO)
            throws IdentificadorInvalidoException, DataPagamentoPrevistoException {

        log.info("Iniciando o tratamento da requisição");
        Servico servicoTratado = ServicoDTO.paraEntidade(servicoDTO);
        servicoTratado.setStatus(StatusServico.EM_ANDAMENTO);
        if (servicoTratado.getPagamentoPrevisto().isAfter(servicoTratado.getPagamentoFinal())) {
            log.error("Data do pagamento previsto não pode ser anterior ao pagamento final");
            throw new DataPagamentoPrevistoException(
                    "Data do pagamento previsto não pode ser anterior ao pagamento final");
        }
        log.info("Serviço tratado para persistência");

        log.info("Procurando o tipo de pagamento");
        TipoPagamento tipoBuscado = tipoPagamentoService.buscarTipoPagamentoPeloId(servicoDTO.getTipoPagamentoId());
        servicoTratado.setTipoPagamento(tipoBuscado);

        log.info("Salvando novo serviço: {}", servicoDTO);
        Servico servico = servicoRepository.save(servicoTratado);

        log.info("Serviço salvo com ID: {}", servico.getId());
        return servico;
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
                    log.error("Falha ao atualizar. Serviço com ID: {} não encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

    @Transactional
    public void deletarServicoPeloId(Long id) throws IdentificadorInvalidoException {
        log.info("Deletando serviço com ID: {}", id);
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
            log.info("Serviço com ID: {} deletado com sucesso", id);
        } else {
            log.error("Falha ao deletar. Serviço com ID: {} não encontrado", id);
            throw new IdentificadorInvalidoException("Identificador do serviço inválido");
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
                    log.error("Falha ao atualizar. Serviço com ID: {} não encontrado", id);
                    return new IdentificadorInvalidoException("Identificador do serviço inválido");
                });
    }

    public HashMap<String, Double> porcentagemStatusServicoTotal(StatusServico status) {
        Double totalStatus = (double) servicoRepository.findAllByStatus(status, null)
                .stream()
                .count();
        Double totalServicos = (double) servicoRepository.count();
        Double porcentagem = (totalStatus / totalServicos) * 100;

        HashMap<String, Double> resultado = new HashMap<>();

        resultado.put("totalStatus", totalStatus);
        resultado.put("totalServicos", totalServicos);
        resultado.put("porcentagem", porcentagem);

        return resultado;
    }

    public List<Servico> buscarTodosServicos() {
        return servicoRepository.findAll();
    }
}
