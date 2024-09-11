package tech.leonam.erp.model.DTO;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ClienteComCpfDTO extends ClienteAbstract {
    @CPF(message = "CPF inválido.")
    private String cpf;
}
