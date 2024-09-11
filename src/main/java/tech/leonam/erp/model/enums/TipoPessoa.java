package tech.leonam.erp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.leonam.erp.util.PessoaJuridica;
import tech.leonam.erp.util.PessoaFisica;

@Getter
@AllArgsConstructor
public enum TipoPessoa {
    FISICA("Física", "CPF", "000.000.000-00", PessoaFisica.class), 
    JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", PessoaJuridica.class);

    private final String descricao;
    private final String documento;
    private final String mascara;
    private final Class<?> group;

}
