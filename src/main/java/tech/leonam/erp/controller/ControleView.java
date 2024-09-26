package tech.leonam.erp.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.model.enums.UF;
import tech.leonam.erp.service.ClienteService;
import tech.leonam.erp.service.ServicoService;
import tech.leonam.erp.service.TipoPagamentoService;

@Controller
@AllArgsConstructor
public class ControleView {

    private final ClienteService clienteService;
    private final ServicoService servicoService;
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
        return "/clientes/cadastro_clientes";
    }

    @GetMapping("/atualizar_cliente")
    public String atualizar_cliente(Model model, @PathVariable @RequestParam Integer id) {
        model.addAttribute("estados", UF.values());
        model.addAttribute("id", id);
        return "/clientes/atualizar_cliente";
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

        return "/clientes/listar_clientes";
    }

    @GetMapping("/servicos_em_andamento")
    public String servicos_em_andamento(Model model, @PathVariable @RequestParam(defaultValue = "1") Integer pagina) {
        var consulta = servicoService.buscarTodosServicos(pagina, 20, "id", "ASC", StatusServico.EM_ANDAMENTO.getCodigo());

        int paginaCorrente = consulta.getNumber();
        int totalPages = consulta.getTotalPages();

        int inicio = Math.max(1, paginaCorrente - 3);
        int fim = Math.min(totalPages, paginaCorrente + 3);
        List<Integer> paginas = IntStream.rangeClosed(inicio, fim).boxed().toList();

        model.addAttribute("servicos", consulta.getContent());
        model.addAttribute("paginaCorrente", paginaCorrente);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginas", paginas);

        return "/servicos/servicos_em_andamento";
    }


    @GetMapping("/servicos_cancelados")
    public String servicos_cancelados(Model model, @PathVariable @RequestParam(defaultValue = "1") Integer pagina) {
        var consulta = servicoService.buscarTodosServicos(pagina, 20, "id", "ASC", StatusServico.CANCELADO.getCodigo());

        int paginaCorrente = consulta.getNumber();
        int totalPages = consulta.getTotalPages();

        int inicio = Math.max(1, paginaCorrente - 3);
        int fim = Math.min(totalPages, paginaCorrente + 3);
        List<Integer> paginas = IntStream.rangeClosed(inicio, fim).boxed().toList();

        model.addAttribute("servicos", consulta.getContent());
        model.addAttribute("paginaCorrente", paginaCorrente);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginas", paginas);

        return "/servicos/servicos_cancelados";
    }

    @GetMapping("/servicos_concluidos")
    public String servicos_concluidos(Model model, @PathVariable @RequestParam(defaultValue = "1") Integer pagina) {
        var consulta = servicoService.buscarTodosServicos(pagina, 20, "id", "ASC", StatusServico.CONCLUIDO.getCodigo());

        int paginaCorrente = consulta.getNumber();
        int totalPages = consulta.getTotalPages();

        int inicio = Math.max(1, paginaCorrente - 3);
        int fim = Math.min(totalPages, paginaCorrente + 3);
        List<Integer> paginas = IntStream.rangeClosed(inicio, fim).boxed().toList();

        model.addAttribute("servicos", consulta.getContent());
        model.addAttribute("paginaCorrente", paginaCorrente);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginas", paginas);

        return "/servicos/servicos_concluidos";
    }

    @GetMapping("/cadastro_servicos")
    public String cadastro_servicos(Model model) {
        model.addAttribute("clientes", clienteService.buscarTodosNomesDosClientes());
        model.addAttribute("tipoPagamentos", tipoPagamentoService.buscarTodosNomesDosTiposDePagamentos());
        return "/servicos/cadastro_servicos";
    }

    @GetMapping("/visualizar_servico")
    public String visualizar_servico(Model model, @PathVariable @RequestParam Long id) throws IdentificadorInvalidoException {
        model.addAttribute("servico", servicoService.buscarServicosPeloId(id));
        return "/servicos/visualizar_servico";
    }

}
