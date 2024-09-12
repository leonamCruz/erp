package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Timespan;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private BigDecimal precoUnitario;
    @NotBlank
    private Long quantidade;
    @Timestamp
    private LocalDateTime validade;
    @Timestamp
    private LocalDateTime dataCadastro;
    @Timestamp
    private LocalDateTime dataDaCompra;
    @NotBlank
    private Long idCategoria;

}
