package tech.leonam.erp.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorTotal;
    @ManyToOne
    private Cliente cliente;
    private String comentario;
    private String formaDePagamento;
    private LocalDateTime dataCadastro;

}
