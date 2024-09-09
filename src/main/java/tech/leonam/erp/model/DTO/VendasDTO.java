package tech.leonam.erp.model.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VendasDTO {

    @NotBlank
    private String servico;
    @NotBlank
    private BigDecimal preco;
    @NotBlank
    private int idCliente;
    @NotBlank
    private String comentario;

}
