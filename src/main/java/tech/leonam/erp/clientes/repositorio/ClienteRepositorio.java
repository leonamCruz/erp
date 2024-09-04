package tech.leonam.erp.clientes.repositorio;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.leonam.erp.clientes.modelo.ClienteModelo;
import org.springframework.dao.EmptyResultDataAccessException;
import tech.leonam.erp.clientes.modelo.ClienteModeloDTO;
import tech.leonam.erp.excessoes.ClienteNaoDeletado;
import tech.leonam.erp.excessoes.ClienteNaoFoiSalvo;

@Repository
@AllArgsConstructor
public class ClienteRepositorio {

    private static final String SELECT_BY_ID = "select * from clientes where id = ?";
    private static final String INSERT_INTO = "INSERT INTO clientes (is_cpf, nome, cpf_or_cnpj, numero_contato, cep, endereco, bairro, cidade, uf, numero_casa) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BY_ID = "delete from clientes where id = ?";
    private static final String EXISTS_ID = "select 1 from clientes where cpf_or_cnpj = ? limit 1";

    private JdbcTemplate jdbcTemplate;

    public ClienteModelo procuraClientePorId(int id) throws EmptyResultDataAccessException {
        var linha = jdbcTemplate.queryForMap(SELECT_BY_ID, id);

        ClienteModelo cliente = new ClienteModelo();
        cliente.setId((Integer) linha.get("id"));
        cliente.setCPF((Boolean) linha.get("is_cpf"));
        cliente.setNome((String) linha.get("nome"));
        cliente.setCpfOrCnpj((String) linha.get("cpf_or_cnpj"));
        cliente.setNumeroContato((String) linha.get("numero_contato"));
        cliente.setCep((String) linha.get("cep"));
        cliente.setEndereco((String) linha.get("endereco"));
        cliente.setBairro((String) linha.get("bairro"));
        cliente.setCidade((String) linha.get("cidade"));
        cliente.setUf((String) linha.get("uf"));
        cliente.setNumeroCasa((Integer) linha.get("numero_casa"));

        return cliente;
    }

    public void salvaCliente(ClienteModeloDTO dto) throws ClienteNaoFoiSalvo {
        var linha = jdbcTemplate.update(INSERT_INTO, dto.isCPF(),
                dto.getNome(),
                dto.getCpfOrCnpj(),
                dto.getNumeroContato(),
                dto.getCep(),
                dto.getEndereco(),
                dto.getBairro(),
                dto.getCidade(),
                dto.getUf(),
                dto.getNumeroCasa());

        if (linha < 0) throw new ClienteNaoFoiSalvo("Não foi possível salvar o cliente.");
    }

    public void deletaPorID(int id) throws ClienteNaoDeletado {
        var linha = jdbcTemplate.update(DELETE_BY_ID, id);

        if (linha < 0) throw new ClienteNaoDeletado("Não foi possível deletar o cliente.");

    }

    public boolean existeID(String cpf) {
        Integer result = null;
        try {
            result = jdbcTemplate.queryForObject(EXISTS_ID, new Object[]{cpf}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // Nenhum resultado foi encontrado, result permanecerá como null
        }
        return result != null;
    }

}
