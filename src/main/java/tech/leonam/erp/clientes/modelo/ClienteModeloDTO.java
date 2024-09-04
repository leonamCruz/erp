package tech.leonam.erp.clientes.modelo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Getter
@Setter
public class ClienteModeloDTO {

    private boolean isCPF;
    private String nome;
    private String cpfOrCnpj;
    private String numeroContato;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private int numeroCasa;

}
