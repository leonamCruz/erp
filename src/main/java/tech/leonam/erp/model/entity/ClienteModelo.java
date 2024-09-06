package tech.leonam.erp.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClienteModelo {

    private int id;
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