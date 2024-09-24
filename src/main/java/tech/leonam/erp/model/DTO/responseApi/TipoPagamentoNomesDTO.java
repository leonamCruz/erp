package tech.leonam.erp.model.DTO.responseApi;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import tech.leonam.erp.model.entity.TipoPagamento;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "nome")
public class TipoPagamentoNomesDTO {
    private Long id;
    private String nome;

    public static TipoPagamentoNomesDTO nome(TipoPagamento m) {
        return new TipoPagamentoNomesDTO(m.getId(), m.getNome());
    }

}
