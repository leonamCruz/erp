package tech.leonam.erp.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.service.TipoPagamentoService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/tipo-pagamento")
public class TipoPagamentoController {

    private final TipoPagamentoService tipoPagamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamento> buscarTipoPagamentoPeloId(@PathVariable Long id)
            throws IdentificadorInvalidoException {
        log.info("Requisição GET para buscar tipo de pagamento com ID: {}", id);
        TipoPagamento tipoPagamento = tipoPagamentoService.buscarTipoPagamentoPeloId(id);
        return ResponseEntity.ok(tipoPagamento);
    }

    @GetMapping
    @Cacheable("tipo-pagamento")
    public ResponseEntity<Page<TipoPagamento>> buscarTodosTiposPagamento(
            @RequestParam(defaultValue = "0") @Min(0) Integer pagina,
            @RequestParam(defaultValue = "10") @Min(1) Integer linhasPorPagina,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direcao) {
        log.info(
                "Requisição GET para buscar todos os tipos de pagamento - Página: {}, Linhas por página: {}, OrderBy: {}, Direção: {}",
                pagina, linhasPorPagina, orderBy, direcao);
        Page<TipoPagamento> tiposPagamento = tipoPagamentoService.buscarTodosTiposPagamento(pagina, linhasPorPagina,
                orderBy, direcao);
        return ResponseEntity.ok(tiposPagamento);
    }

    @PostMapping
    public ResponseEntity<Long> salvarTipoPagamento(@RequestBody TipoPagamentoDTO tipoPagamentoDTO) {
        log.info("Requisição POST para salvar novo tipo de pagamento: {}", tipoPagamentoDTO);
        Long id = tipoPagamentoService.salvarTipoPagamento(tipoPagamentoDTO).getId();
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarTipoPagamento(@PathVariable Long id,
            @RequestBody TipoPagamentoDTO tipoPagamentoDTO) {
        log.info("Requisição PUT para atualizar tipo de pagamento com ID: {}. Dados: {}", id, tipoPagamentoDTO);
        try {
            Long updatedId = tipoPagamentoService.atualizarTipoPagamento(id, tipoPagamentoDTO).getId();
            return ResponseEntity.ok(updatedId);
        } catch (IdentificadorInvalidoException e) {
            log.error("Erro ao atualizar tipo de pagamento com ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoPagamentoPeloId(@PathVariable Long id) throws IdentificadorInvalidoException {
        log.info("Requisição DELETE para deletar tipo de pagamento com ID: {}", id);
        tipoPagamentoService.deletarTipoPagamentoPeloId(id);
        return ResponseEntity.noContent().build();
    }
}
