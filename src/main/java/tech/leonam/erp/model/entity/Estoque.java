package tech.leonam.erp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomeDoProduto;
    private int quantidade;
    private LocalDateTime validade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataDaCompra;

}
