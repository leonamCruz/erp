package tech.leonam.erp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPagamento {
    CREDITO(1, "Crédito"),
    DEBITO(2, "Débito"),
    BOLETO(3, "Boleto"),
    PIX(4, "PIX"),
    PAYPAL(5, "PayPal");

    private final int codigo;
    private final String descricao;

    public static TipoPagamento fromCodigo(int codigo) {
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de pagamento inválido: " + codigo);
    }
}
