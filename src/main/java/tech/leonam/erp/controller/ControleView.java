package tech.leonam.erp.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.enums.UF;
import tech.leonam.erp.service.ClienteService;
import tech.leonam.erp.service.TipoPagamentoService;

@Controller
@AllArgsConstructor
public class ControleView {

    private final ClienteService clienteService;
    private final TipoPagamentoService tipoPagamentoService;

    @GetMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/sidebar")
    public String sidebar(Model model) {
        return "sidebar";
    }

    @GetMapping("/vendas")
    public String vendas(Model model) {
        return "vendas";
    }

    @GetMapping("/cadastro_clientes")
    public String cadastro_clientes(Model model) {
        model.addAttribute("estados", UF.values());
        return "cadastro_clientes";
    }

    @GetMapping("/atualizar_cliente")
    public String atualizar_cliente(Model model, @PathVariable @RequestParam Integer id) {
        model.addAttribute("estados", UF.values());
        model.addAttribute("id", id);
        return "atualizar_cliente";
    }

    @GetMapping("/listar_clientes")
    public String listar_clientes(Model model, @PathVariable @RequestParam(defaultValue = "1") Integer pagina) {
        var consulta = clienteService.buscarTodosOsCLientes(pagina, 20, "id", "ASC");

        int paginaCorrente = consulta.getNumber();
        int totalPages = consulta.getTotalPages();

        int inicio = Math.max(1, paginaCorrente - 3);
        int fim = Math.min(totalPages, paginaCorrente + 3);
        List<Integer> paginas = IntStream.rangeClosed(inicio, fim).boxed().toList();

        model.addAttribute("clientes", consulta.getContent());
        model.addAttribute("paginaCorrente", paginaCorrente);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginas", paginas);

        return "listar_clientes";
    }

    @GetMapping("/deletar_clientes")
    public String deletar_clientes(Model model) {
        return "deletar_clientes";
    }

    @GetMapping("/cadastro_servicos")
    public String cadastro_servicos(Model model) {
        model.addAttribute("clientes", clienteService.buscarTodosNomesDosClientes());
        model.addAttribute("tipoPagamentos", tipoPagamentoService.buscarTodosNomesDosTiposDePagamentos());
        return "cadastro_servicos";
    }

}
