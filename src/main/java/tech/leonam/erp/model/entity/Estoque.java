package tech.leonam.erp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomeDoProduto;

    @ManyToOne
    private Categoria categoria;
    private BigDecimal precoUnitario;
    private BigDecimal quantidade;

    private LocalDateTime validade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataDaCompra;

}
