package tech.leonam.erp.util;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import tech.leonam.erp.model.entity.Cliente;
import tech.leonam.erp.model.enums.TipoPessoa;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

    @Override
    public List<Class<?>> getValidationGroups(final Cliente cliente) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(Cliente.class);

        if (cliente != null) {
            if (TipoPessoa.FISICA == cliente.getTipoPessoa()) {
                groups.add(PessoaFisica.class);
            } else if (TipoPessoa.JURIDICA == cliente.getTipoPessoa()) {
                groups.add(PessoaJuridica.class);
            }
        }

        return groups;
    }

}
