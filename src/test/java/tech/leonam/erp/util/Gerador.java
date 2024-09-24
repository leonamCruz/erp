package tech.leonam.erp.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import tech.leonam.erp.model.DTO.ClienteDTO;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;

@Component
@ActiveProfiles("dev")
public class Gerador {
        
        public static ServicoDTO getServicoDTO() {
                return ServicoDTO.builder()
                                .id(null)
                                .nome("Consultoria de TI")
                                .preco(new BigDecimal("1500.00"))
                                .clienteId(1l)
                                .descricao("Consultoria especializada em soluções de tecnologia")
                                .tipoPagamentoId(1l)
                                .status(StatusServico.PENDENTE)
                                .pagamentoPrevisto(LocalDate.of(2024, 10, 15))
                                .pagamentoFinal(LocalDate.of(2024, 10, 20))
                                .modificadoPor("QUINZINHO BOLADÃO")
                                .dataModificacao(null)
                                .dataCriacao(null)
                                .criadoPor("QUINZINHO BOLADÃO")
                                .build();
        }

        public static TipoPagamentoDTO getTipoPagamentoDTO() {
                return TipoPagamentoDTO.builder()
                                .id(null)
                                .nome("Cartão de Crédito")
                                .descricao("Pagamento via cartão de crédito com parcelamento em até 12 vezes")
                                .ativo(true)
                                .dataModificacao(LocalDateTime.of(2024, 10, 20, 12, 0))
                                .modificadoPor("GAUNALDINHO ROCHO")
                                .dataCriacao(LocalDateTime.of(2024, 10, 20, 12, 0))
                                .criadoPor("GAUNALDINHO ROCHO")
                                .build();
        }

        public static ClienteDTO getClienteDTO() {
                return ClienteDTO.builder()
                                .id(null)
                                .nome("Joao Silva")
                                .numeroContato("1234567890")
                                .cep("12345678")
                                .identificacao("12345678909")
                                .endereco("Rua Exemplo, 123")
                                .bairro("Centro")
                                .cidade("Sao Paulo")
                                .uf("SP")
                                .numeroCasa(100)
                                .build();
        }

        public static Page<Servico> getPaginaServicos() {
                List<Servico> servicos = List.of(
                                Servico.builder()
                                                .id(1L)
                                                .nome("Servico 1")
                                                .descricao("Descricao do servico 1")
                                                .build(),
                                Servico.builder()
                                                .id(2L)
                                                .nome("Servico 2")
                                                .descricao("Descricao do servico 2")
                                                .build(),
                                Servico.builder()
                                                .id(3L)
                                                .nome("Servico 3")
                                                .descricao("Descricao do servico 3")
                                                .build());

                return new PageImpl<>(
                                servicos, 
                                PageRequest.of(0, 10), 
                                servicos.size()
                );
        }
}
