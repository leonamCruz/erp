package tech.leonam.erp.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class VendasModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String servico;
    private BigDecimal valorTotal;
    private Long idCliente;
    private String comentario;
    private String formaDePagamento;
    private LocalDateTime dataCadastro;

}
