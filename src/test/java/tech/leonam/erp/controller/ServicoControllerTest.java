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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tech.leonam.erp.model.DTO.ServicoDTO;
import tech.leonam.erp.model.entity.Servico;
import tech.leonam.erp.model.enums.StatusServico;
import tech.leonam.erp.service.ServicoService;
import tech.leonam.erp.util.Gerador;

@AutoConfigureMockMvc
@ActiveProfiles("dev")
@WebMvcTest(controllers = ServicoController.class)
public class ServicoControllerTest {

    static String SERVICO_API = "/api/servicos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicoService servicoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Deve criar um servico")
    public void deveCriarUmServico() throws Exception {
        ServicoDTO servicoDTO = Gerador.getServicoDTO();
        servicoDTO.setId(1l);
        Servico servicoSalvo = ServicoDTO.paraEntidade(Gerador.getServicoDTO());
        servicoSalvo.setId(1l);

        Mockito.when(servicoService.salvarServico(Mockito.any(ServicoDTO.class)))
                .thenReturn(servicoSalvo);

        String json = objectMapper.writeValueAsString(servicoDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(SERVICO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("1"));

    }

    @Test
    @DisplayName("Deve buscar todos os serviços com paginação")
    public void deveBuscarTodosOsServicos() throws Exception {
        Page<Servico> servicos = Gerador.getPaginaServicos();
        Mockito.when(servicoService.buscarTodosServicos(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString()))
                .thenReturn(servicos);

        mockMvc.perform(MockMvcRequestBuilders.get(SERVICO_API)
                .param("pagina", "0")
                .param("linhasPorPagina", "10")
                .param("orderBy", "id")
                .param("direcao", "ASC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    @DisplayName("Deve atualizar um serviço")
    public void deveAtualizarUmServico() throws Exception {
        ServicoDTO servicoDTO = Gerador.getServicoDTO();
        servicoDTO.setId(1L);
        Servico servicoSalvo = ServicoDTO.paraEntidade(Gerador.getServicoDTO());
        servicoSalvo.setId(1L);

        Mockito.when(servicoService.atualizarServico(Mockito.eq(servicoDTO.getId()), Mockito.any(ServicoDTO.class)))
                .thenReturn(servicoSalvo.getId());

        String json = objectMapper.writeValueAsString(servicoDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(SERVICO_API + "/" + servicoDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    @DisplayName("Deve deletar um serviço pelo ID")
    public void deveDeletarServicoPeloId() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(servicoService).deletarServicoPeloId(id);

        mockMvc.perform(MockMvcRequestBuilders.delete(SERVICO_API + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve alterar o status de um serviço")
    public void deveAlterarStatusDoServico() throws Exception {
        Long id = 1L;
        StatusServico status = StatusServico.EM_ANDAMENTO;

        Mockito.doNothing().when(servicoService).alterarStatusDoServico(id, status);

        mockMvc.perform(MockMvcRequestBuilders.patch(SERVICO_API + "/" + id + "/status")
                .param("status", status.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
