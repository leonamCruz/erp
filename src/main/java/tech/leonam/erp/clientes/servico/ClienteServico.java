package tech.leonam.erp.clientes.servico;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tech.leonam.erp.clientes.modelo.ClienteModelo;
import tech.leonam.erp.clientes.modelo.ClienteModeloDTO;
import tech.leonam.erp.clientes.repositorio.ClienteRepositorio;
import tech.leonam.erp.excessoes.ClienteNaoDeletado;
import tech.leonam.erp.excessoes.ClienteNaoExiste;
import tech.leonam.erp.excessoes.ClienteNaoFoiSalvo;

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
    public boolean idExisteNoBD(int id) throws ClienteNaoExiste {
        return repositorio.existeID(id);
    }
}
