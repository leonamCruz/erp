package tech.leonam.erp.vendas.modelo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class VendasModeloDTO {

    @NotBlank
    private String servico;
    @NotBlank
    private BigDecimal preco;
    @NotBlank
    private int idCliente;
    @NotBlank
    private String comentario;

}
