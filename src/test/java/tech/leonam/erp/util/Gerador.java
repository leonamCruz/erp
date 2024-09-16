package tech.leonam.erp.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import tech.leonam.erp.model.DTO.ClienteDTO;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.enums.StatusServico;

@Configuration
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
                .pagamentoPrevisto(LocalDateTime.of(2024, 10, 15, 12, 0))
                .pagamentoFinal(LocalDateTime.of(2024, 10, 20, 12, 0))
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
                .dataModificacao(null)
                .modificadoPor("GAUNALDINHO ROCHO")
                .dataCriacao(null)
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
}
