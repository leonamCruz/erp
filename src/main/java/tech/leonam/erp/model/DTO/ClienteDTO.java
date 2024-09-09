package tech.leonam.erp.model.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.erp.model.entity.Cliente;

@Data
@Getter
@Setter
@Builder
public class ClienteDTO {

    private Long id;

    @NotBlank
    private String nome;

    // @CPF
    private String cpf;

    // @CNPJ
    private String cnpj;

    @NotBlank
    private String numeroContato;

    @NotBlank
    private String cep;

    @NotBlank
    private String endereco;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotBlank
    private Integer numeroCasa;

    private LocalDateTime dataCadastro;

    public static Cliente paraEntidade(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(null)
                .nome(clienteDTO.getNome())
                .cpf(clienteDTO.getCpf())
                .cnpj(clienteDTO.getCnpj())
                .numeroContato(clienteDTO.getNumeroContato())
                .cep(clienteDTO.getCep())
                .endereco(clienteDTO.getEndereco())
                .bairro(clienteDTO.getBairro())
                .cidade(clienteDTO.getCidade())
                .uf(clienteDTO.getUf())
                .numeroCasa(clienteDTO.getNumeroCasa())
                .dataCadastro(null)
                .build();
    }

    public static ClienteDTO paraDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .cnpj(cliente.getCnpj())
                .numeroContato(cliente.getNumeroContato())
                .cep(cliente.getCep())
                .endereco(cliente.getEndereco())
                .bairro(cliente.getBairro())
                .cidade(cliente.getCidade())
                .uf(cliente.getUf())
                .numeroCasa(cliente.getNumeroCasa())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }

}
