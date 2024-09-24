package tech.leonam.erp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leonam.erp.model.DTO.EstoqueDTO;
import tech.leonam.erp.model.entity.Cliente;
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
}


