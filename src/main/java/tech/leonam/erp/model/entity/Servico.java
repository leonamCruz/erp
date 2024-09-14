package tech.leonam.erp.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.leonam.erp.model.enums.StatusServico;

/**
 * Entidade que representa um serviço oferecido.
 */
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Servico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Lob
    @Column(nullable = true)
    private String descricao;

    @ManyToOne
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private StatusServico status;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime pagamentoPrevisto;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime pagamentoFinal;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataModificacao;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String criadoPor;

    @Column
    @LastModifiedBy
    private String modificadoPor;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.dataModificacao = LocalDateTime.now();
    }
}