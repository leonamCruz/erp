package tech.leonam.erp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.enums.UF;

@Controller
@AllArgsConstructor
public class ControleView {

    @GetMapping("/cadastrarclientes")
    public String cadastroDeClientes(Model model) {
        model.addAttribute("estados", UF.values());
        return "cadastro_clientes";
    }

    @GetMapping("/vendas")
    public String vendas(Model model) {
        return "vendas";
    }

}
