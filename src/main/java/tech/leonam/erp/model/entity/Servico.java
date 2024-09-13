package tech.leonam.erp.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.model.enums.TipoPagamento;

@Data
@Getter
@Setter
@Entity
public class Servico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Positive
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Cliente cliente;

    @Lob
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoPagamento formaDePagamento;

    @Enumerated(EnumType.STRING)
    private StatusServico status;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime pagamentoPrevista;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime pagamentoFinal;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

}
