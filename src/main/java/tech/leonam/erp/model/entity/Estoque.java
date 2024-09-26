package tech.leonam.erp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;

    @ManyToOne
    private Categoria categoria;
    private BigDecimal precoUnitario;
    private BigDecimal quantidade;

    private LocalDateTime validade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataDaCompra;

}
