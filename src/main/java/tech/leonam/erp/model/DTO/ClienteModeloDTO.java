package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ClienteModeloDTO {

    @NotBlank
    private String nome;
    private String cpf;
    private String cnpj;
    @NotBlank
    private String numeroContato;
    @NotBlank
    private String cep;
    @NotBlank
    private String endereco;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @NotBlank
    private Long numeroCasa;

}
