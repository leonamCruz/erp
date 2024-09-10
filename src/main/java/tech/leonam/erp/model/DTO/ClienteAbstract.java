package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClienteAbstract {
    private static final String NOT_BLANK = "Campo Nulo ou Vazio";

    @NotBlank(message = NOT_BLANK)
    private String nome;

    @NotBlank(message = NOT_BLANK)
    @Size(min = 10, max = 13, message = "Verifique o tamanho do contato")
    private String numeroContato;

    @NotBlank(message = "CEP: " + NOT_BLANK)
    @Size(min = 8, message = "CEP: Tamanho minimo é 8")
    private String cep;

    @NotBlank(message = "Endereço: " + NOT_BLANK)
    private String endereco;

    @NotBlank(message = "Bairro: " + NOT_BLANK)
    private String bairro;

    @NotBlank(message = "Cidade: " + NOT_BLANK)
    private String cidade;

    @NotBlank(message = "Estado: " + NOT_BLANK)
    private String uf;

    @NotNull( message = "Número da Casa: " + NOT_BLANK)
    private int numeroCasa;

}
