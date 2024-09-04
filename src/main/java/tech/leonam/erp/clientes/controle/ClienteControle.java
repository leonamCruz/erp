package tech.leonam.erp.clientes.controle;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leonam.erp.clientes.modelo.ClienteModelo;
import tech.leonam.erp.clientes.modelo.ClienteModeloDTO;
import tech.leonam.erp.clientes.servico.ClienteServico;
import tech.leonam.erp.excessoes.ClienteNaoDeletado;
import tech.leonam.erp.excessoes.ClienteNaoFoiSalvo;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteControle {

    private final ClienteServico servico;

    @PostMapping
    public ResponseEntity<ClienteModelo> salvarCliente(@RequestBody ClienteModeloDTO dto) {
        try {
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
