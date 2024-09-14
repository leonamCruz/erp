package tech.leonam.erp.controller;

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
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServicoPeloId(@PathVariable @Min(1) Long id) {
        try {
            Servico servico = servicoService.buscarServicosPeloId(id);
            return new ResponseEntity<>(servico, HttpStatus.OK);
        } catch (IdentificadorInvalidoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Cacheable("servicos")
    public ResponseEntity<Page<Servico>> buscarTodosServicos(
            @RequestParam(defaultValue = "0") @Min(0) Integer pagina,
            @RequestParam(defaultValue = "10") @Min(1) Integer linhasPorPagina,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direcao) {
        Page<Servico> servicos = servicoService.buscarTodosServicos(pagina, linhasPorPagina, orderBy, direcao);
        return ResponseEntity.ok().body(servicos);
    }

    @PostMapping
    public ResponseEntity<Long> salvarServico(@Valid @RequestBody ServicoDTO servicoDTO) throws IdentificadorInvalidoException {
        Long id = servicoService.salvarServico(servicoDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarServico(@PathVariable @Min(1) Long id,
            @Valid @RequestBody ServicoDTO servicoDTO) {
        try {
            Long idAtualizado = servicoService.atualizarServico(id, servicoDTO);
            return new ResponseEntity<>(idAtualizado, HttpStatus.OK);
        } catch (IdentificadorInvalidoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServicoPeloId(@PathVariable @Min(1) Long id) {
        try {
            servicoService.deletarServicoPeloId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IdentificadorInvalidoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatusDoServico(@PathVariable @Min(1) Long id,
            @RequestParam StatusServico status) {
        try {
            servicoService.alterarStatusDoServico(id, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IdentificadorInvalidoException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
