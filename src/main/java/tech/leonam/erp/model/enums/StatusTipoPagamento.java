package tech.leonam.erp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * Status corrente do tipo de pagamento
 */

@Getter
@AllArgsConstructor
public enum StatusTipoPagamento {

    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo"),
    PENDENTE(3, "Pendente");

    private final int codigo;
    private final String descricao;

    public static StatusTipoPagamento fromCodigo(int codigo) {
        for (StatusTipoPagamento tipo : StatusTipoPagamento.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de pagamento inválido: " + codigo);
    }
}