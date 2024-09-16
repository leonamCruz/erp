package tech.leonam.erp.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @apiNote
 *          Entidade que representa um tipo de pagamento, sua ideia principal é
 *          para que o cliente tenha controle de processos de pagamento e se
 *          seram
 *          alterados ou não em um futuro próximo. Sua principal caracteristica
 *          está
 *          presente em sua relação com o serviço
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
public class TipoPagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String nome;

    @Lob
    @Column(nullable = false)
    private String descricao;

    @Column
    private Boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoPagamento", fetch = FetchType.LAZY)
    private Set<Servico> servicos;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String criadoPor;

    @Column
    @LastModifiedBy
    private String modificadoPor;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataModificacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataModificacao = LocalDateTime.now();
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}