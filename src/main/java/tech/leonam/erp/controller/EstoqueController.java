package tech.leonam.erp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leonam.erp.model.DTO.EstoqueDTO;
import tech.leonam.erp.model.entity.Estoque;
import tech.leonam.erp.service.EstoqueService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/estoque")
public class EstoqueController {
    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<String> salvarEstoque(@RequestBody @Valid EstoqueDTO dto) {
        estoqueService.salvarEstoque(dto);
        return ResponseEntity.ok().body("Estoque salvo com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoque() {
        return ResponseEntity.ok().body(estoqueService.retornaTodoEstoque());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoquePorId(id);
        return ResponseEntity.ok().body(String.format("O id: %d foi deletado com sucesso", id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> alterarEstoque(@PathVariable Long id, @RequestBody @Valid EstoqueDTO dto) {
        estoqueService.alteraEstoque(dto, id);
        return ResponseEntity.ok().body("Estoque alterado com sucesso");
    }
}


