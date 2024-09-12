package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import tech.leonam.erp.util.PessoaFisica;
import tech.leonam.erp.util.PessoaJuridica;

@Getter
@Setter
@Builder
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Size(min = 10, max = 13, message = "Verifique o tamanho do contato")
    private String numeroContato;

    @Size(min = 8, message = "CEP: Tamanho minimo é 8")
    private String cep;

    @CPF(groups = PessoaFisica.class, message = "CPF inválido")
    @CNPJ(groups = PessoaJuridica.class, message = "CNPJ inválido")
    private String identificacao;

    private String endereco;

    private String bairro;

    private String cidade;

    private String uf;

    private int numeroCasa;

}
