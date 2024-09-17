package tech.leonam.erp.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.util.Gerador;

@Transactional
@SpringBootTest
@ActiveProfiles("dev")
public class TipoPagamentoServiceTest {

    private Long id = 1L;

    @Autowired
    private TipoPagamentoService tipoPagamentoService;

    @Test
    @DisplayName("Deve buscar o tipo de pagamento por id")
    public void deveBuscarTipoPagamentoPorId() throws IdentificadorInvalidoException {
        TipoPagamentoDTO tipoPagamentoDTO = Gerador.getTipoPagamentoDTO();

        tipoPagamentoService.salvarTipoPagamento(tipoPagamentoDTO);

        TipoPagamento resultado = tipoPagamentoService.buscarTipoPagamentoPeloId(id);

        Assertions.assertThat(resultado.getId()).isEqualTo(id).isNotNull();
    }

    @Test
    @DisplayName("Deve salvar um tipo de pagamento")
    public void deveSalvarUmTipoPagamento() {
        TipoPagamentoDTO tipoPagamentoDTO = Gerador.getTipoPagamentoDTO();

        TipoPagamento resultado = tipoPagamentoService.salvarTipoPagamento(tipoPagamentoDTO);

        Assertions.assertThat(resultado.getId()).isNotNull();
        Assertions.assertThat(resultado.getNome()).isEqualTo(tipoPagamentoDTO.getNome());
        Assertions.assertThat(resultado.getDescricao()).isEqualTo(tipoPagamentoDTO.getDescricao());
        Assertions.assertThat(resultado.getAtivo()).isEqualTo(tipoPagamentoDTO.getAtivo());
        Assertions.assertThat(resultado.getDataModificacao()).isNotNull();
        Assertions.assertThat(resultado.getModificadoPor()).isEqualTo(tipoPagamentoDTO.getModificadoPor());
        Assertions.assertThat(resultado.getDataCriacao()).isNotNull();
        Assertions.assertThat(resultado.getCriadoPor()).isEqualTo(tipoPagamentoDTO.getCriadoPor());

    }

    @Test
    @DisplayName("Deve atualizar um tipo de pagamento")
    public void deveAtualizarTipoPagamento() throws IdentificadorInvalidoException {
        TipoPagamentoDTO tipoPagamentoDTO = Gerador.getTipoPagamentoDTO();
        tipoPagamentoDTO.setNome("teste");
        tipoPagamentoDTO.setCriadoPor("teste");

        TipoPagamento tipoPagamentoEsperado = tipoPagamentoService.atualizarTipoPagamento(id, tipoPagamentoDTO);

        Assertions.assertThat(tipoPagamentoEsperado.getNome()).isEqualTo(tipoPagamentoDTO.getNome());
        Assertions.assertThat(tipoPagamentoEsperado.getCriadoPor()).isEqualTo(tipoPagamentoDTO.getCriadoPor());
    }

    @Test
    @DisplayName("Deve deletar o tipo de pagamento por id")
    public void deveDeletarTipoPagamentoPeloId() throws IdentificadorInvalidoException {
        TipoPagamento tipoPagamentoBuscado = tipoPagamentoService.buscarTipoPagamentoPeloId(id);

        Assertions.assertThat(tipoPagamentoBuscado.getId()).isNotNull();

        tipoPagamentoService.deletarTipoPagamentoPeloId(id);

        org.junit.jupiter.api.Assertions
                .assertThrows(IdentificadorInvalidoException.class,
                        () -> tipoPagamentoService.buscarTipoPagamentoPeloId(id));

    }

}
