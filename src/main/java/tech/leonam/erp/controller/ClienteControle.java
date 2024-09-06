package tech.leonam.erp.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.model.DTO.ClienteModeloDTO;
import tech.leonam.erp.model.entity.ClienteModelo;
import tech.leonam.erp.service.ClienteServico;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteControle {

    private final ClienteServico servico;

    @PostMapping
    public ResponseEntity<ClienteModelo> salvarCliente(@RequestBody ClienteModeloDTO dto) {
        try {
            if (servico.cpfExiste(dto.getCpfOrCnpj())) return ResponseEntity.status(HttpStatus.CONFLICT).build();

            servico.salvarCliente(dto);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoFoiSalvo e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModelo> buscarCliente(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(servico.procuraAtravesDoId(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable int id){
        try {
            servico.deletaClientePorId(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoDeletado e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable int id, @RequestBody ClienteModeloDTO dto){
        return null;
    }


}
