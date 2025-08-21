package br.gov.caixa.hackaton.unitarios.controller;

import br.gov.caixa.hackaton.service.TelemetriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TelemetriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TelemetriaService telemetriaService;

    @Test
    void retornoSucessoParaEndpointConsultarTelemetriaPorDataTest() throws Exception {


        MvcResult res = mockMvc.perform(get("/telemetria?dataReferencia=21-08-2025")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(res);
    }

    @Test
    void retornoErroParaEndpointConsultarTelemetriaComDataInvalidaTest() throws Exception {


        MvcResult res = mockMvc.perform(get("/telemetria?dataReferencia=21-08-")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertNotNull(res);
    }
}
