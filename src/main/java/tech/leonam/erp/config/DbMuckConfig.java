package tech.leonam.erp.config;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.repository.ClienteRepository;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class DbMuckConfig {

    private final ClienteRepository clienteRepository;

    @Bean
    public String saveAll() {
        List<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(null, "Ana Silva", "12345678900", null, "11987654321", "01000000", "Avenida Presidente Juscelino Kubitschek de OliveiraAvenida Presidente Juscelino Kubitschek de OliveiraAvenida Presidente Juscelino  A", "Centro", "Sao Paulo", "SP", 123, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Joao Oliveira", "23456789011", null, "11987654322", "02000000", "Rua B", "Jardim", "Sao Paulo", "SP", 456, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Maria Santos", "34567890122", null, "11987654323", "03000000", "Rua C", "Vila", "Sao Paulo", "SP", 789, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Pedro Almeida", "45678901233", null, "11987654324", "04000000", "Rua D", "Sao Pedro", "Sao Paulo", "SP", 101, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Lucas Costa", "56789012344", null, "11987654325", "05000000", "Rua E", "Bela Vista", "Sao Paulo", "SP", 202, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Mariana Lima", "67890123455", null, "11987654326", "06000000", "Rua F", "Lapa", "Sao Paulo", "SP", 303, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Roberto Pereira", "78901234566", null, "11987654327", "07000000", "Rua G", "Sao Joao", "Sao Paulo", "SP", 404, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Fernanda Martins", "89012345677", null, "11987654328", "08000000", "Rua H", "Vila Mariana", "Sao Paulo", "SP", 505, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Carlos Rodrigues", "90123456788", null, "11987654329", "09000000", "Rua I", "Morumbi", "Sao Paulo", "SP", 606, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Patricia Souza", "01234567899", null, "11987654330", "10000000", "Rua J", "Pinheiros", "Sao Paulo", "SP", 707, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Ricardo Silva", null, "12345678000100", "11987654331", "11000000", "Rua K", "Vila Olimpica", "Sao Paulo", "SP", 808, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Juliana Costa", null, "23456789000111", "11987654332", "12000000", "Rua L", "Liberdade", "Sao Paulo", "SP", 909, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Thiago Almeida", null, "34567890000122", "11987654333", "13000000", "Rua M", "Consolacao", "Sao Paulo", "SP", 1010, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Vanessa Lima", null, "45678901000133", "11987654334", "14000000", "Rua N", "Moema", "Sao Paulo", "SP", 1111, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Felipe Santos", null, "56789012000144", "11987654335", "15000000", "Rua O", "Itaim Bibi", "Sao Paulo", "SP", 1212, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Gabriela Rodrigues", null, "67890123000155", "11987654336", "16000000", "Rua P", "Aclimacao", "Sao Paulo", "SP", 1313, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Eduardo Pereira", null, "78901234000166", "11987654337", "17000000", "Rua Q", "Alto da Lapa", "Sao Paulo", "SP", 1414, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Juliana Martins", null, "89012345000177", "11987654338", "18000000", "Rua R", "Bexiga", "Sao Paulo", "SP", 1515, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Danilo Oliveira", null, "90123456000188", "11987654339", "19000000", "Rua S", "Brooklin", "Sao Paulo", "SP", 1616, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Larissa Souza", null, "01234567000199", "11987654340", "20000000", "Rua T", "Jardim Paulista", "Sao Paulo", "SP", 1717, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Mateus Lima", null, "12345678000100", "11987654341", "21000000", "Rua U", "Vila Prudente", "Sao Paulo", "SP", 1818, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Claudia Silva", null, "23456789000111", "11987654342", "22000000", "Rua V", "Santo Amaro", "Sao Paulo", "SP", 1919, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Bruno Costa", null, "34567890000122", "11987654343", "23000000", "Rua W", "Vila Maria", "Sao Paulo", "SP", 2020, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Carla Santos", null, "45678901000133", "11987654344", "24000000", "Rua X", "Perdizes", "Sao Paulo", "SP", 2121, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Lucas Rodrigues", null, "56789012000144", "11987654345", "25000000", "Rua Y", "Vila Leopoldina", "Sao Paulo", "SP", 2222, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Tatiane Pereira", null, "67890123000155", "11987654346", "26000000", "Rua Z", "Sao Bernardo", "Sao Paulo", "SP", 2323, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Tatiane Pereira", null, "67890123000155", "11987654346", "26000000", "Rua Z", "Sao Bernardo", "Sao Paulo", "SP", 2323, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Tatiane Pereira", null, "67890123000155", "11987654346", "26000000", "Rua Z", "Sao Bernardo", "Sao Paulo", "SP", 2323, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Tatiane Pereira", null, "67890123000155", "11987654346", "26000000", "Rua Z", "Sao Bernardo", "Sao Paulo", "SP", 2323, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Tatiane Pereira", null, "67890123000155", "11987654346", "26000000", "Rua Z", "Sao Bernardo", "Sao Paulo", "SP", 2323, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Andre Martins", null, "78901234000166", "11987654347", "27000000", "Rua AA", "Jardim Sao Paulo", "Sao Paulo", "SP", 2424, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Marcela Lima", null, "89012345000177", "11987654348", "28000000", "Rua AB", "Jardim Marajoara", "Sao Paulo", "SP", 2525, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Gustavo Almeida", null, "90123456000188", "11987654349", "29000000", "Rua AC", "Vila Galvao", "Sao Paulo", "SP", 2626, LocalDateTime.now()));
        clientes.add(new Cliente(null, "Renata Souza", null, "01234567000199", "11987654350", "30000000", "Rua AD", "Vila Ipojuca", "Sao Paulo", "SP", 2727, LocalDateTime.now()));

        clienteRepository.saveAll(clientes);
        return null;
    }
}
