package tech.leonam.erp.model.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class ClienteComCnpjDTO extends ClienteAbstract {
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;
}
