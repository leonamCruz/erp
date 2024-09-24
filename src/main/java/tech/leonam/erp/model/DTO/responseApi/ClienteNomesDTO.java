package tech.leonam.erp.model.DTO.responseApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.leonam.erp.model.entity.Cliente;

@Getter
@AllArgsConstructor
public class ClienteNomesDTO {
    private Long id;
    private String nome;

    public static ClienteNomesDTO nome(Cliente cliente){
        return new ClienteNomesDTO(cliente.getId(), cliente.getNome());
    }
}
