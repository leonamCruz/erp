package tech.leonam.erp.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CPF(groups = PessoaFisica.class, message = "CPF inválido")
    @CNPJ(groups = PessoaJuridica.class, message = "CNPJ inválido")
    private String identificacao;

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
