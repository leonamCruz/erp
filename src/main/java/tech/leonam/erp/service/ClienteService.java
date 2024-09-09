package tech.leonam.erp.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ClienteDTO;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.repository.ClienteRepository;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public void salvarCliente(ClienteDTO clienteDTO) throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {
        if (clienteRepository.existsByCpf(clienteDTO.getCpf()))
            throw new IdentificadorInvalidoException("CPF já cadastrado " + clienteDTO.getCpf() + " já cadastrado");
        if (clienteRepository.existsByCnpj(clienteDTO.getCnpj()))
            throw new IdentificadorInvalidoException("CNPJ já cadastrado " + clienteDTO.getCnpj() + " já cadastrado");
        Cliente cliente = ClienteDTO.paraEntidade(clienteDTO);
        clienteRepository.save(cliente);
    }

    public Cliente procuraAtravesDoId(Long id) throws IdentificadorInvalidoException {
        return clienteRepository.findById(id)
                .orElseThrow(
                        () -> new IdentificadorInvalidoException("Cliente com o id " + id + " não foi encontrado"));
    }

    public void deletaClientePorId(Long id) throws ClienteNaoDeletado {
        clienteRepository.deleteById(id);
    }

    public void atualizarCliente(ClienteDTO clienteDTO, Long id) throws ClienteNaoFoiSalvo {
        clienteRepository.findById(id)
                .map(m -> {
                    clienteDTO.setId(id);
                    clienteRepository.save(ClienteDTO.paraEntidade(clienteDTO));
                    return Void.TYPE;
                }).orElseThrow(() -> new ClienteNaoFoiSalvo(
                        "Erro ao deletar cliente, por favor entre em contato com os desenvolvedores."));
    }
}
