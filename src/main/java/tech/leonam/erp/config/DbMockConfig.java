package tech.leonam.erp.config;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.model.enums.UF;
import tech.leonam.erp.repository.ClienteRepository;
import tech.leonam.erp.util.DocumentoGerador;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class DbMockConfig {

    private final ClienteRepository clienteRepository;
    private final Faker faker;
    private static final List<String> BAIRROS = Arrays.asList(
            "Jardim Paulista", "Vila Madalena", "Centro", "Moema", "Pinheiros",
            "Brooklin", "Santana", "Aclimação", "Vila Mariana", "Itaim Bibi"
    );

    @Bean
    public String saveAll() {
        var fakes = geradorDeFake(100);
        clienteRepository.saveAll(fakes);
        return null;
    }

    private List<Cliente> geradorDeFake(int quantidade){
        List<Cliente> clientes = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        for(var i = 0; i < quantidade; i++){
            Cliente cliente = new Cliente();

            cliente.setDataCadastro(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));

            cliente.setNome(faker.name().fullName());
            cliente.setNumeroContato(faker.phoneNumber().phoneNumber());
            cliente.setCep(faker.address().zipCode());

            if(random.nextBoolean()) cliente.setIdentificacao(DocumentoGerador.gerarCPF());
            else cliente.setIdentificacao(DocumentoGerador.gerarCNPJ());

            cliente.setEndereco(faker.address().streetName());
            cliente.setBairro(BAIRROS.get(random.nextInt(BAIRROS.size())));
            cliente.setCidade(faker.address().city());
            cliente.setUf(UF.estadoAleatorio());
            cliente.setNumeroCasa(random.nextInt(0, 2000));

            clientes.add(cliente);
        }
        return clientes;
    }
}
