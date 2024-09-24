package tech.leonam.erp.service;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import tech.leonam.erp.exceptions.ClienteNaoFoiSalvo;
import tech.leonam.erp.exceptions.DataPagamentoPrevistoException;
import tech.leonam.erp.exceptions.IdentificadorInvalidoException;
import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.repository.TipoPagamentoRepository;
import tech.leonam.erp.util.Gerador;

@Transactional
@SpringBootTest
@ActiveProfiles("dev")
public class ServicoServiceTest {

    @Autowired
    private ClienteService clienteRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private ServicoService servicoService;

    private Long id = 1l;

    @Test
    @DisplayName("Deve salvar um serviço")
    public void deveSalvarServico() throws IdentificadorInvalidoException, ClienteNaoFoiSalvo, DataPagamentoPrevistoException {
        ServicoDTO servicoDTO = Gerador.getServicoDTO();

        clienteRepository.salvarCliente(Gerador.getClienteDTO());
        tipoPagamentoRepository.save(TipoPagamentoDTO.paraEntidade(Gerador.getTipoPagamentoDTO()));

        Servico servico = servicoService.salvarServico(servicoDTO);

        Assertions.assertThat(servico.getId()).isNotNull();
        Assertions.assertThat(servico.getNome()).isEqualTo(servicoDTO.getNome());
        Assertions.assertThat(servico.getPreco()).isEqualTo(servicoDTO.getPreco());
        Assertions.assertThat(servico.getDescricao()).isEqualTo(servicoDTO.getDescricao());
        Assertions.assertThat(servico.getTipoPagamento().getId()).isEqualTo(servicoDTO.getTipoPagamentoId());
        Assertions.assertThat(servico.getStatus()).isEqualTo(servicoDTO.getStatus());
        Assertions.assertThat(servico.getPagamentoPrevisto()).isEqualTo(servicoDTO.getPagamentoPrevisto());
        Assertions.assertThat(servico.getPagamentoFinal()).isEqualTo(servicoDTO.getPagamentoFinal());
        Assertions.assertThat(servico.getModificadoPor()).isEqualTo(servicoDTO.getModificadoPor());
        Assertions.assertThat(servico.getDataModificacao()).isNotNull();
        Assertions.assertThat(servico.getDataCriacao()).isNotNull();
        Assertions.assertThat(servico.getCliente().getId()).isEqualTo(servicoDTO.getClienteId());
        Assertions.assertThat(servico.getCriadoPor()).isEqualTo(servicoDTO.getCriadoPor());

    }

    @Test
    @DisplayName("Deve buscar um serviço por id")
    public void deveBuscarServicoPorId() throws IdentificadorInvalidoException, DataPagamentoPrevistoException {

        ServicoDTO servicoDTO = Gerador.getServicoDTO();

        servicoService.salvarServico(servicoDTO);

        Servico servicoBuscado = servicoService.buscarServicosPeloId(id);

        Assertions.assertThat(servicoBuscado.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("Deve deletar um servico por id")
    public void deveDeletarUmServicoPorId() throws IdentificadorInvalidoException {
        Servico servicoBuscado = servicoService.buscarServicosPeloId(id);

        Assertions.assertThat(servicoBuscado.getId()).isNotNull();

        servicoService.deletarServicoPeloId(id);

        org.junit.jupiter.api.Assertions
                .assertThrows(IdentificadorInvalidoException.class,
                        () -> servicoService.buscarServicosPeloId(id));

    }

    @Test
    @DisplayName("Deve atualizar um servico")
    public void deveAtualiazarUmServico() throws IdentificadorInvalidoException{
        ServicoDTO servicoEsperado = Gerador.getServicoDTO();
        servicoEsperado.setNome("teste");
        servicoEsperado.setPreco(new BigDecimal(0));
        servicoEsperado.setDescricao("teste");
        servicoEsperado.setStatus(StatusServico.INATIVO);

        Long idRetornado = servicoService.atualizarServico(id, servicoEsperado);

        Servico servicoRetornado = servicoService.buscarServicosPeloId(idRetornado);

        Assertions.assertThat(idRetornado).isEqualTo(id);
        Assertions.assertThat(servicoRetornado.getNome()).isEqualTo("teste");
        Assertions.assertThat(servicoRetornado.getPreco()).isEqualTo(new BigDecimal(0));
        Assertions.assertThat(servicoRetornado.getDescricao()).isEqualTo("teste");
        Assertions.assertThat(servicoRetornado.getStatus()).isEqualTo(StatusServico.INATIVO);

    }

    @Test
    @DisplayName("Deve atualizar os status de um servio")
    public void deveAtualiazarOsStatusDeUmServico() throws IdentificadorInvalidoException, DataPagamentoPrevistoException{
        ServicoDTO servicoDTO = Gerador.getServicoDTO();

        servicoDTO.setStatus(StatusServico.PENDENTE);

        Servico servicoSalvo = servicoService.salvarServico(servicoDTO);

        Assertions.assertThat(servicoSalvo.getStatus()).isEqualTo(StatusServico.PENDENTE);

        servicoService.alterarStatusDoServico(id, StatusServico.ATIVO);

        Servico servicoRetornado = servicoService.buscarServicosPeloId(id);

        Assertions.assertThat(servicoRetornado.getStatus()).isEqualTo(StatusServico.ATIVO);

    }

}
