package tech.leonam.erp.model.DTO;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.model.enums.StatusServico;

/**
 * DTO que representa a entidade serviço.
 */

@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O nome do serviço é obrigatório e não pode estar em branco")
    @Size(min = 1, max = 250, message = "O nome do serviço deve conter entre 1 e 250 caracteres")
    private String nome;

    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preço deve ser superior a 0")
    @Digits(integer = 12, fraction = 2, message = "O preço deve ter no máximo 6 dígitos inteiros e 2 decimais")
    private BigDecimal preco;

    @Valid 
    @NotNull(message = "O cliente é obrigatório e não pode ser nulo")
    private ClienteDTO clienteDTO;

    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A forma de pagamento é obrigatória")
    private TipoPagamento tipoPagamento;

    @NotNull(message = "O status do serviço é obrigatório")
    private StatusServico status;

    @NotNull(message = "A data de pagamento prevista é obrigatória")
    @Future(message = "A data de pagamento prevista deve ser uma data futura")
    private LocalDateTime pagamentoPrevista;

    @NotNull(message = "A data de pagamento final é obrigatória")
    @FutureOrPresent(message = "A data de pagamento final deve ser no presente ou no futuro")
    @AssertTrue(message = "A data de pagamento final deve ser posterior ou igual à data prevista de pagamento")
    private LocalDateTime pagamentoFinal;

    private String modificadoPor;
    private LocalDateTime dataCriacao;
    private String criadoPor;
}
