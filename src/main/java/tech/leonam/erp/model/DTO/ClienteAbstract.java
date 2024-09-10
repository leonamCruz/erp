package tech.leonam.erp.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public abstract class ClienteAbstract {
   // @NotBlank
    private String nome;

    //@NotBlank
    private String numeroContato;

    //@NotBlank
    private String cep;

   // @NotBlank
    private String endereco;

    //@NotBlank
    private String bairro;

   // @NotBlank
    private String cidade;

    //@NotBlank
    private String uf;

    //@NotBlank
    private int numeroCasa;

    private LocalDateTime dataCadastro;

}
