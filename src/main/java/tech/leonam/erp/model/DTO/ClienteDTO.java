package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDTO {

    private Long id;

    private String nome;

    @Size(min = 10, max = 13, message = "Verifique o tamanho do contato")
    private String numeroContato;

    @Size(min = 8, message = "CEP: Tamanho minimo é 8")
    private String cep;

    private String identificacao;

    private String endereco;

    private String bairro;

    private String cidade;

    private String uf;

    private int numeroCasa;

}
