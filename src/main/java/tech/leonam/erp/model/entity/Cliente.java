package tech.leonam.erp.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.leonam.erp.model.enums.TipoPessoa;
import tech.leonam.erp.util.ClienteGroupSequenceProvider;
import tech.leonam.erp.util.PessoaFisica;
import tech.leonam.erp.util.PessoaJuridica;

@Data
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
@NoArgsConstructor
public class Cliente implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String identificacao;

    @Transient
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    private String numeroContato;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer numeroCasa;
    private LocalDateTime dataCadastro;

}
