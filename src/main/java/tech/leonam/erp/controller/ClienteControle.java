package tech.leonam.erp.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ClienteDTO;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.service.ClienteService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteControle {

    private final ClienteService clienteServico;
    private static final String PADRAO_LINHAS_POR_PAGINA = "20";
    private static final String PADRAO_PAGINA = "0";
    private static final String PADRAO_DE_ORDEM = "id";
    private static final String PADRAO_DE_DIRECAO = "ASC";


    @PostMapping
    public ResponseEntity<String> salvarCliente(@RequestBody @Valid ClienteDTO clienteDto) {
        try {
            clienteServico.salvarCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente criado com sucesso!");
        } catch (ClienteNaoFoiSalvo e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (IdentificadorInvalidoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) throws IdentificadorInvalidoException {
        try {
            return ResponseEntity.ok().body(clienteServico.procuraAtravesDoId(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cliente>> buscarTodosOsCLientes(
        @PathVariable @RequestParam(defaultValue = PADRAO_PAGINA) Integer pagina,
        @RequestParam(defaultValue = PADRAO_LINHAS_POR_PAGINA) Integer linhasPorPagina,
        @PathVariable @RequestParam(defaultValue = PADRAO_DE_ORDEM) String orderBy,
        @RequestParam(defaultValue = PADRAO_DE_DIRECAO) String direcao) {
        
        Page<Cliente> clientes = clienteServico.buscarTodosOsCLientes(pagina, linhasPorPagina, orderBy, direcao);
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        try {
            clienteServico.deletaClientePorId(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoDeletado e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
        try {
            clienteServico.atualizarCliente(clienteDto, id);
            return ResponseEntity.ok().body("Cliente alterado com sucesso");
        } catch (ClienteNaoFoiSalvo e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar cliente, por favor entre em contato com os desenvolvedores.");
        } catch (IdentificadorInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
