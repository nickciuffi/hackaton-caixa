package br.gov.caixa.hackaton.unitarios.controller;

import br.gov.caixa.hackaton.dto.simulacao.SimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.service.SimulacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
 class SimulacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SimulacaoService simulacaoService;

    private static final BigDecimal VALOR_DESEJADO = new BigDecimal("9000");
    private static final Integer PRAZO = 20;
    private static final BigDecimal VALOR_TOTAL = new BigDecimal("1200.00");
    private static final Integer ID_SIMULACAO = 123;
    private static final String TIPO_SIMULACAO_SAC = "SAC";
    private static final String TIPO_SIMULACAO_PRICE = "PRICE";

    @Test
    void retornoSucessoParaEndpointRealizarSimulacaoTest() throws Exception {
        when(simulacaoService.realizarSimulacao(any())).thenReturn(new SimulacaoResponseDTO());

        SimulacaoRequestDTO req = new SimulacaoRequestDTO(VALOR_DESEJADO, PRAZO);
        String json = objectMapper.writeValueAsString(req);

        MvcResult res = mockMvc.perform(post("/simulacao/realizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoErroParaEndpointRealizarSimulacaoFaltandoCamposJsonTest() throws Exception {

        SimulacaoRequestDTO req = new SimulacaoRequestDTO(VALOR_DESEJADO, null);
        String json = objectMapper.writeValueAsString(req);

        MvcResult res = mockMvc.perform(post("/simulacao/realizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoErroParaEndpointRealizarSimulacaoComValorNegativOJsonTest() throws Exception {

        SimulacaoRequestDTO req = new SimulacaoRequestDTO(new BigDecimal("-2"), PRAZO);
        String json = objectMapper.writeValueAsString(req);

        MvcResult res = mockMvc.perform(post("/simulacao/realizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoSucessoParaEndpointConsultarSimulacoesComPaginacaoTest() throws Exception {

        SimulacaoDTO sim1 = SimulacaoDTO
                .builder()
                .idSimulacao(ID_SIMULACAO)
                .prazo(PRAZO)
                .valorDesejado(VALOR_DESEJADO)
                .valorTotalParcelas(VALOR_TOTAL)
                .tipoSimulacao(TIPO_SIMULACAO_SAC)
                .build();
        SimulacaoDTO sim2 = SimulacaoDTO
                .builder()
                .idSimulacao(ID_SIMULACAO)
                .prazo(PRAZO)
                .valorDesejado(VALOR_DESEJADO)
                .valorTotalParcelas(VALOR_TOTAL)
                .tipoSimulacao(TIPO_SIMULACAO_PRICE)
                .build();

        List<SimulacaoDTO> resService =  new ArrayList<>(Arrays.asList(sim1, sim2));
        when(simulacaoService.consultarSimulacoes()).thenReturn(resService);

        MvcResult res = mockMvc.perform(get("/simulacao?pagina=2&qtdRegistrosPagina=89")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoErroParaEndpointConsultarSimulacoesComPaginacaoFaltandoParametrosTest() throws Exception {

        MvcResult res = mockMvc.perform(get("/simulacao?qtdRegistrosPagina=89")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoSucessoParaEndpointConsultarSimulacoesPorDataEProdTest() throws Exception {

        MvcResult res = mockMvc.perform(get("/simulacao/prod-data?codigoProduto=1&dataReferencia=21-08-2025")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoErroParaEndpointConsultarSimulacoesPorDataEProdFaltandoParametrosTest() throws Exception {

        MvcResult res = mockMvc.perform(get("/simulacao/prod-data?dataReferencia=21-08-2025")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertNotNull(res);
    }
}
