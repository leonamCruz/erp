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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * DTO que representa um tipo de pagamento.
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoPagamentoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O ID não pode ser nulo")
    @Positive(message = "O ID deve ser um valor positivo")
    private Long id;

    @NotBlank(message = "O nome do tipo de pagamento é obrigatório")
    @Size(max = 250, min = 1 , message = "O nome deve ter no máximo 250 caracteres")
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

}
