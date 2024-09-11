package tech.leonam.erp.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.model.enums.TipoPessoa;
import tech.leonam.erp.repository.ClienteRepository;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class DbMockConfig {

    private final ClienteRepository clienteRepository;

    @Bean
    public String saveAll() {
        List<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(null, "Ana Silva", "49640855030", TipoPessoa.FISICA, "31996709760", "12345-678", "Rua Augusta", "Bela Vista", "São Paulo", "SP", 10, LocalDateTime.now()));
        clientes.add(new Cliente(null, "João Santos", "09013988024", TipoPessoa.FISICA, "21987654321", "12345-679", "Rua do Ouvidor", "Centro", "Rio de Janeiro", "RJ", 20, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Maria Oliveira", "11171646089", TipoPessoa.FISICA, "31988765432", "12345-680", "Avenida Afonso Pena", "Centro", "Belo Horizonte", "MG", 30, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pedro Costa", "78947690023", TipoPessoa.FISICA, "51998877665", "12345-681", "Rua dos Andradas", "Centro", "Porto Alegre", "RS", 40, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Carlos Souza", "31731146043", TipoPessoa.FISICA, "71999887766", "12345-682", "Rua das Flores", "Centro", "Salvador", "BA", 50, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Empresa A", "35415578000193", TipoPessoa.JURIDICA, "31987654321", "12345-683", "Avenida Paulista", "Consolação", "São Paulo", "SP", 60, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Empresa B", "51127563000186", TipoPessoa.JURIDICA, "21995554444", "12345-684", "Rua da Assembleia", "Centro", "Rio de Janeiro", "RJ", 70, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Empresa C", "63031006000146", TipoPessoa.JURIDICA, "31996665555", "12345-685", "Avenida Contorno", "Centro", "Belo Horizonte", "MG", 80, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Empresa D", "00802977000122", TipoPessoa.JURIDICA, "51997776666", "12345-686", "Rua dos Alfeneiros", "Centro", "Porto Alegre", "RS", 90, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Empresa E", "43506290000152", TipoPessoa.JURIDICA, "71993334444", "12345-687", "Avenida Tancredo Neves", "Pituba", "Salvador", "BA", 100, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 11", "89893678080", TipoPessoa.FISICA, "31991234567", "12345-688", "Rua do Lavradio", "Centro", "São Paulo", "SP", 11, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 12", "49708356000173", TipoPessoa.JURIDICA, "21998887766", "12345-689", "Avenida Rio Branco", "Centro", "Rio de Janeiro", "RJ", 12, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 13", "09606975002", TipoPessoa.FISICA, "31999888877", "12345-690", "Rua Sergipe", "Centro", "Belo Horizonte", "MG", 13, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 14", "78554048000139", TipoPessoa.JURIDICA, "51996667777", "12345-691", "Rua da Praia", "Centro", "Porto Alegre", "RS", 14, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 15", "87181802054", TipoPessoa.FISICA, "71997778888", "12345-692", "Rua do Bomfim", "Centro", "Salvador", "BA", 15, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 16", "80634077000152", TipoPessoa.JURIDICA, "31999998888", "12345-693", "Avenida Tancredo Neves", "Pituba", "São Paulo", "SP", 16, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 17", "61448554080", TipoPessoa.FISICA, "21993334444", "12345-694", "Rua do Mercado", "Centro", "Rio de Janeiro", "RJ", 17, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 18", "55426984000169", TipoPessoa.JURIDICA, "31998887777", "12345-695", "Rua Goiás", "Centro", "Belo Horizonte", "MG", 18, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 19", "47961184031", TipoPessoa.FISICA, "51992223344", "12345-696", "Rua da República", "Centro", "Porto Alegre", "RS", 19, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 20", "80593910000164", TipoPessoa.JURIDICA, "71995554444", "12345-697", "Avenida Sete de Setembro", "Centro", "Salvador", "BA", 20, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 21", "93322450023", TipoPessoa.FISICA, "31991122334", "12345-698", "Rua da Consolação", "Consolação", "São Paulo", "SP", 21, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 22", "71991083000176", TipoPessoa.JURIDICA, "21996668888", "12345-699", "Rua da Assembleia", "Centro", "Rio de Janeiro", "RJ", 22, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 23", "51896854036", TipoPessoa.FISICA, "31994445555", "12345-700", "Rua Francisco Sá", "Centro", "Belo Horizonte", "MG", 23, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Jurídica 24", "61795787000110", TipoPessoa.JURIDICA, "51998887777", "12345-701", "Rua Marechal Floriano", "Centro", "Porto Alegre", "RS", 24, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pessoa Física 25", "58496703002", TipoPessoa.FISICA, "71992223334", "12345-702", "Rua Professor Sabino", "Centro", "Salvador", "BA", 25, LocalDateTime.now()));

        clienteRepository.saveAll(clientes);
        return null;
    }
}
