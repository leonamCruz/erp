package tech.leonam.erp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusServico {

    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo"),
    PENDENTE(3, "Pendente");

    private final int codigo;
    private final String descricao;

    public static StatusServico fromCodigo(int codigo) {
        for (StatusServico tipo : StatusServico.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de pagamento inválido: " + codigo);
    }
}
