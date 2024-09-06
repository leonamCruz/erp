package tech.leonam.erp.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tech.leonam.erp.exceptions.ClienteNaoDeletado;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.model.DTO.ClienteModeloDTO;
import tech.leonam.erp.model.entity.ClienteModelo;
import tech.leonam.erp.repository.ClienteRepositorio;


@Service
@AllArgsConstructor
public class ClienteServico {
    private final ClienteRepositorio repositorio;

    public void salvarCliente(ClienteModeloDTO dto) throws ClienteNaoFoiSalvo {
        repositorio.salvaCliente(dto);

    }
    public ClienteModelo procuraAtravesDoId(int id) throws EmptyResultDataAccessException {
        return repositorio.procuraClientePorId(id);
    }

    public void deletaClientePorId(int id) throws ClienteNaoDeletado {
        repositorio.deletaPorID(id);
    }
    public boolean cpfExiste(String cpf){
        return repositorio.existeID(cpf);
    }
}
