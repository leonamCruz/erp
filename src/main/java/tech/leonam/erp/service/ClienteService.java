package tech.leonam.erp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ClienteComCnpjDTO;
import tech.leonam.erp.model.DTO.ClienteComCpfDTO;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.repository.ClienteRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public void salvarCliente(ClienteComCnpjDTO clienteDTO) throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {
        existeCnpjOuDaThrow(clienteDTO.getCnpj());

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.setDataCadastro(LocalDateTime.now());
        clienteRepository.save(cliente);
    }

    public void salvarCliente(ClienteComCpfDTO clienteDTO) throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {
        existeCpfOuDaThrow(clienteDTO.getCpf());

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.setDataCadastro(LocalDateTime.now());
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
    public void verificaSeExisteIdOuDaThrow(Long id) throws IdentificadorInvalidoException {
        if (!clienteRepository.existsById(id)) throw new IdentificadorInvalidoException("Cliente com o id " + id + " não foi encontrado");
    }

    public void existeCnpjOuDaThrow(String cnpj) throws IdentificadorInvalidoException{
        if (clienteRepository.existsByCnpj(cnpj))
            throw new IdentificadorInvalidoException("CNPJ já cadastrado " + cnpj + " já cadastrado");
    }
    public void existeCpfOuDaThrow(String cpf) throws IdentificadorInvalidoException{
        if (clienteRepository.existsByCpf(cpf))
            throw new IdentificadorInvalidoException("CPF já cadastrado " + cpf + " já cadastrado");
    }

    public void atualizarCliente(ClienteComCnpjDTO clienteDTO, Long id) throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {
        verificaSeExisteIdOuDaThrow(id);

        Cliente clienteAtualizado = modelMapper.map(clienteDTO, Cliente.class);
        clienteAtualizado.setId(id);
        clienteRepository.save(clienteAtualizado);

    }
}
