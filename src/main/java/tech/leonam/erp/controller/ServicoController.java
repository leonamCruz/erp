package tech.leonam.erp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import tech.leonam.erp.exceptions.DataPagamentoPrevistoException;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final String PADRAO_LINHAS_POR_PAGINA = "20";
    private final String PADRAO_PAGINA = "0";
    private final String PADRAO_DE_ORDEM = "id";
    private final String PADRAO_DE_DIRECAO = "ASC";

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServicoPeloId(@PathVariable @Min(1) Long id) throws IdentificadorInvalidoException {
        Servico servico = servicoService.buscarServicosPeloId(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @GetMapping
    @Cacheable("servicos")
    public ResponseEntity<Page<Servico>> buscarTodosServicos(
            @RequestParam(defaultValue = PADRAO_PAGINA) @Min(0) Integer pagina,
            @RequestParam(defaultValue = PADRAO_LINHAS_POR_PAGINA) @Min(1) Integer linhasPorPagina,
            @RequestParam(defaultValue = PADRAO_DE_ORDEM) String orderBy,
            @RequestParam(defaultValue = PADRAO_DE_DIRECAO) String direcao,
            @RequestParam(defaultValue = "2") Integer status) {
        Page<Servico> servicos = servicoService.buscarTodosServicos(pagina, linhasPorPagina, orderBy, direcao, status);
        return ResponseEntity.ok().body(servicos);
    }

    @PostMapping
    public ResponseEntity<Long> salvarServico(@Valid @RequestBody ServicoDTO servicoDTO)
            throws IdentificadorInvalidoException, DataPagamentoPrevistoException {
        Long id = servicoService.salvarServico(servicoDTO).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarServico(@PathVariable @Min(1) Long id,
            @Valid @RequestBody ServicoDTO servicoDTO) throws IdentificadorInvalidoException {
            Long idAtualizado = servicoService.atualizarServico(id, servicoDTO);
            return new ResponseEntity<>(idAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServicoPeloId(@PathVariable @Min(1) Long id) throws IdentificadorInvalidoException {
            servicoService.deletarServicoPeloId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/atualizar")
    public ResponseEntity<Void> alterarStatusDoServico(@PathVariable @Min(1) Long id,
            @RequestParam StatusServico status) throws IdentificadorInvalidoException {
        servicoService.alterarStatusDoServico(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("porcentagemStatusServicoTotal")
    public HashMap<String, Double> getMethodName(@RequestParam(defaultValue = "2") Integer status) {
        return servicoService.porcentagemStatusServicoTotal(StatusServico.fromCodigo(status));
    }
    

}
