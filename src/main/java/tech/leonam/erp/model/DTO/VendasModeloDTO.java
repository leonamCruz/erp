package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
