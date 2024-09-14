package tech.leonam.erp.model.DTO;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.leonam.erp.model.entity.TipoPagamento;

/**
 * DTO que representa um tipo de pagamento.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoPagamentoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O ID não pode ser nulo")
    @Positive(message = "O ID deve ser um valor positivo")
    private Long id;

    @NotBlank(message = "O nome do tipo de pagamento é obrigatório")
    @Size(max = 250, min = 1, message = "O nome deve ter no máximo 250 caracteres")
    private String nome;

    @Lob
    @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    private String descricao;

    @NotNull(message = "O status de ativo é obrigatório")
    private Boolean ativo;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataModificacao;

    private String modificadoPor;
    private LocalDateTime dataCriacao;
    private String criadoPor;

    public static TipoPagamento paraEntidade(TipoPagamentoDTO tipoPagamentoDTO) {
        return TipoPagamento.builder()
                .id(tipoPagamentoDTO.getId())
                .nome(tipoPagamentoDTO.getNome())
                .descricao(tipoPagamentoDTO.getDescricao())
                .ativo(tipoPagamentoDTO.getAtivo())
                .criadoPor(tipoPagamentoDTO.getCriadoPor())
                .modificadoPor(tipoPagamentoDTO.getModificadoPor())
                .dataCriacao(tipoPagamentoDTO.getDataCriacao())
                .dataModificacao(tipoPagamentoDTO.getDataModificacao())
                .build();
    }

    public static TipoPagamentoDTO paraDTO(TipoPagamento tipoPagamento) {
        return TipoPagamentoDTO.builder()
                .id(tipoPagamento.getId())
                .nome(tipoPagamento.getNome())
                .descricao(tipoPagamento.getDescricao())
                .ativo(tipoPagamento.getAtivo())
                .criadoPor(tipoPagamento.getCriadoPor())
                .modificadoPor(tipoPagamento.getModificadoPor())
                .dataCriacao(tipoPagamento.getDataCriacao())
                .dataModificacao(tipoPagamento.getDataModificacao())
                .build();
    }

}
