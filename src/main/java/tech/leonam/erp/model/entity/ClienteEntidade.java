package tech.leonam.erp.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ClienteEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String numeroContato;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private int numeroCasa;
    private LocalDateTime dataCadastro;

}
