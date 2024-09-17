package tech.leonam.erp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tech.leonam.erp.model.DTO.TipoPagamentoDTO;
import tech.leonam.erp.model.entity.TipoPagamento;
import tech.leonam.erp.service.TipoPagamentoService;

@AutoConfigureMockMvc
@ActiveProfiles("dev")
@WebMvcTest(TipoPagamentoController.class)
public class TipoPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoPagamentoService tipoPagamentoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    @DisplayName("Deve buscar um tipo de pagamento pelo ID")
    public void deveBuscarTipoPagamentoPeloId() throws Exception {
        Long id = 1L;
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setId(id);

        Mockito.when(tipoPagamentoService.buscarTipoPagamentoPeloId(id))
                .thenReturn(tipoPagamento);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tipo-pagamento/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @DisplayName("Deve buscar todos os tipos de pagamento")
    public void deveBuscarTodosTiposPagamento() throws Exception {
        Page<TipoPagamento> tiposPagamento = Page.empty(); // Use uma página de exemplo
        Mockito.when(tipoPagamentoService.buscarTodosTiposPagamento(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(tiposPagamento);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tipo-pagamento")
                .param("pagina", "0")
                .param("linhasPorPagina", "10")
                .param("orderBy", "id")
                .param("direcao", "ASC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }


    @Test
    @DisplayName("Deve atualizar um tipo de pagamento")
    public void deveAtualizarTipoPagamento() throws Exception {
        Long id = 1L;
        TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO();
        tipoPagamentoDTO.setId(id);
        TipoPagamento tipoPagamentoAtualizado = new TipoPagamento();
        tipoPagamentoAtualizado.setId(id);

        Mockito.when(tipoPagamentoService.atualizarTipoPagamento(Mockito.eq(id), Mockito.any(TipoPagamentoDTO.class)))
                .thenReturn(tipoPagamentoAtualizado);

        String json = objectMapper.writeValueAsString(tipoPagamentoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tipo-pagamento/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(id.toString()));
    }

    @Test
    @DisplayName("Deve deletar um tipo de pagamento pelo ID")
    public void deveDeletarTipoPagamentoPeloId() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(tipoPagamentoService).deletarTipoPagamentoPeloId(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tipo-pagamento/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
