package tech.leonam.erp.vendas.modelo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class VendasModelo {

    private long id;
    private String servico;
    private BigDecimal valorTotal;
    private int idCliente;
    private String comentario;
    private LocalDateTime dataCadastro;
    private String formaDePagamento;

}
