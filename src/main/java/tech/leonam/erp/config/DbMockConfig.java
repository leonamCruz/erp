package tech.leonam.erp.config;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.model.enums.UF;
import tech.leonam.erp.repository.ClienteRepository;
import tech.leonam.erp.repository.ServicoRepository;
import tech.leonam.erp.repository.TipoPagamentoRepository;
import tech.leonam.erp.util.DocumentoGerador;

@Configuration
@Profile("dev")
@AllArgsConstructor
public class DbMockConfig {

    private final ClienteRepository clienteRepository;
    private final TipoPagamentoRepository tipoPagamentoRepository;
    private final ServicoRepository servicoRepository;
    private final Faker faker;
    private static final List<String> BAIRROS = Arrays.asList(
            "Jardim Paulista", "Vila Madalena", "Centro", "Moema", "Pinheiros",
            "Brooklin", "Santana", "Aclimação", "Vila Mariana", "Itaim Bibi");
    private final Integer quantidade = 100;
    
    @Bean
    public String saveAllCliente() {
        var fakes = geradorDeClienteFake();
        clienteRepository.saveAll(fakes);
        return null;
    }

    @Bean
    public String saveAllTipoPagamento() {
        var fakes = geradorDeTipoPagamentoFake();
        tipoPagamentoRepository.saveAll(fakes);
        return null;
    }

    @Bean
    public String saveAllServico() {
        List<Servico> fakes = geradorDeServicoFake();
        servicoRepository.saveAll(fakes);
        return null;
    }

    private List<Cliente> geradorDeClienteFake() {
        List<Cliente> clientes = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        for (var i = 0; i < quantidade; i++) {
            Cliente cliente = new Cliente();

            cliente.setNome(faker.name().fullName());
            cliente.setNumeroContato(faker.phoneNumber().phoneNumber());
            cliente.setCep(faker.address().zipCode());

            if (random.nextBoolean())
                cliente.setIdentificacao(DocumentoGerador.gerarCPF());
            else
                cliente.setIdentificacao(DocumentoGerador.gerarCNPJ());

            cliente.setEndereco(faker.address().streetName());
            cliente.setBairro(BAIRROS.get(random.nextInt(BAIRROS.size())));
            cliente.setCidade(faker.address().city());
            cliente.setUf(UF.estadoAleatorio());
            cliente.setNumeroCasa(random.nextInt(0, 2000));

            cliente.setCriadoPor(faker.name().nameWithMiddle());
            cliente.setModificadoPor(faker.name().nameWithMiddle());
            cliente.setDataModificacao(LocalDateTime.now().minusDays(random.nextInt(0, 365)));

            clientes.add(cliente);
        }
        return clientes;
    }

    
    private List<Servico> geradorDeServicoFake() {
        List<Servico> servicos = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        for (var i = 0; i < quantidade; i++) {
            Servico servico = new Servico();

            servico.setNome(faker.commerce().productName());
            servico.setPreco(BigDecimal.valueOf(faker.number().randomDouble(2, 50, 500)));
            servico.setCliente(
                Cliente.builder()
                .id(longSobreQuatidade())
                .build()
            );
            servico.setDescricao(faker.lorem().sentence());
            servico.setTipoPagamento(
                    TipoPagamento.builder()
                            .id(longSobreQuatidade())
                            .build());
            servico.setStatus(StatusServico.values()[random.nextInt(StatusServico.values().length)]);
            servico.setPagamentoPrevisto(LocalDateTime.now().plusDays(random.nextInt(1, 30)));
            servico.setPagamentoFinal(LocalDateTime.now().plusDays(random.nextInt(31, 60)));
            servico.setCriadoPor(faker.name().fullName());
            servico.setModificadoPor(faker.name().fullName());

            servicos.add(servico);
        }
        return servicos;
    }

    private List<TipoPagamento> geradorDeTipoPagamentoFake() {
        List<TipoPagamento> tiposPagamento = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        for (var i = 0; i < quantidade; i++) {
            TipoPagamento tipoPagamento = new TipoPagamento();

            tipoPagamento.setNome(faker.job().seniority());
            tipoPagamento.setDescricao(faker.lorem().sentence());
            tipoPagamento.setAtivo(faker.bool().bool());
            tipoPagamento.setCriadoPor(faker.name().fullName());
            tipoPagamento.setModificadoPor(faker.name().fullName());
            tipoPagamento.setDataCriacao(LocalDateTime.now().minusDays(random.nextInt(0, 365)));
            tipoPagamento.setDataModificacao(LocalDateTime.now().minusDays(random.nextInt(0, 365)));

            tiposPagamento.add(tipoPagamento);
        }
        return tiposPagamento;
    }


    private Long longSobreQuatidade(){
        return faker.number().numberBetween(1l, quantidade);
    }

}
