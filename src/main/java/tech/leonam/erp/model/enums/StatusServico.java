package tech.leonam.erp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * Status corrente do serviço
 */

@Getter
@AllArgsConstructor
public enum StatusServico {

    CONCLUIDO(1, "Concluído"),
    EM_ANDAMENTO(2, "Em andamento"),
    CANCELADO(3, "Cancelado");

    private final int codigo;
    private final String descricao;

    public static StatusServico fromCodigo(int codigo) {
        for (StatusServico status : StatusServico.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}