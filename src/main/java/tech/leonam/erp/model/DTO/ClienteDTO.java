package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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

    @Size(min = 5, max = 255, message = "Verifique o campo de endereço")
    private String endereco;

    @Size(min = 3, max = 255, message = "Verifique o campo do bairro")
    private String bairro;

    @Size(min = 3, max = 255, message = "Verifique o campo da cidade")
    private String cidade;

    @Size(min = 2, max = 2, message = "Verifique o campo do estado")
    private String uf;

    @PositiveOrZero(message = "Verifique o número da casa")
    private int numeroCasa;

}
