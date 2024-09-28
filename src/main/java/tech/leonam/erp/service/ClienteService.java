package tech.leonam.erp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ClienteDTO;
import tech.leonam.erp.model.DTO.responseApi.ClienteNomesDTO;
import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.model.enums.TipoPessoa;
import tech.leonam.erp.repository.ClienteRepository;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public void salvarCliente(ClienteDTO clienteDTO) throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
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

    public void atualizarCliente(ClienteDTO clienteDTO, Long id)
            throws ClienteNaoFoiSalvo, IdentificadorInvalidoException {
        verificaSeExisteIdOuDaThrow(id);

        Cliente clienteTratado = modelMapper.map(clienteDTO, Cliente.class);

        if (clienteDTO.getIdentificacao().length() == 11) {
            clienteTratado.setTipoPessoa(TipoPessoa.FISICA);
        }

        if (clienteDTO.getIdentificacao().length() == 14) {
            clienteTratado.setTipoPessoa(TipoPessoa.JURIDICA);
        }

        clienteRepository.findById(id).map(m -> {
            clienteTratado.setId(m.getId());
            clienteRepository.save(clienteTratado);
            return Void.TYPE;
        }).orElseThrow(() -> new IdentificadorInvalidoException("Cliente com o id " + id + " não foi encontrado"));

    }

    public Page<Cliente> buscarTodosOsClientes(Integer pagina, Integer linhasPorPagina, String orderBy,
                                               String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public List<Cliente> buscarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    public void verificaSeExisteIdOuDaThrow(Long id) throws IdentificadorInvalidoException {
        if (!clienteRepository.existsById(id))
            throw new IdentificadorInvalidoException("Cliente com o id " + id + " não foi encontrado");
    }

    public List<ClienteNomesDTO> buscarTodosNomesDosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(m -> ClienteNomesDTO.nome(m))
                .toList();
    }
}
