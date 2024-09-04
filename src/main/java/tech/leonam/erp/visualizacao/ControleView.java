package tech.leonam.erp.visualizacao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tech.leonam.erp.infos.servico.EstadosServico;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ControleView {

    private EstadosServico estadosServico;

    @GetMapping("/cadastrarclientes")
    public String cadastroDeClientes(Model model) {
        Map<String, String> estadosMap = estadosServico.pegaTodosOsEstados();

        List<Map.Entry<String, String>> estadosOrdenados = estadosMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        model.addAttribute("estados", estadosOrdenados);
        return "cadastro_clientes";
    }

    @GetMapping("/vendas")
    public String vendas(Model model) {
        return "vendas";
    }

}
