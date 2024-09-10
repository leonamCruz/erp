package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import tech.leonam.erp.model.entity.Cliente;

import java.time.LocalDateTime;

@Data
public class ClienteComCpfDTO extends ClienteAbstract {
    @CPF(message = "CPF inválido.")
    private String cpf;
}
