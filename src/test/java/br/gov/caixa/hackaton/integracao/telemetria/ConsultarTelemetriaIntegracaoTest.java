package br.gov.caixa.hackaton.integracao.telemetria;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.repository.local.ParcelaRepository;
import br.gov.caixa.hackaton.repository.local.SimulacaoRepository;
import br.gov.caixa.hackaton.repository.local.TelemetriaRepository;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
 class ConsultarTelemetriaIntegracaoTest {

    @Autowired
    private SimulacaoRepository simulacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TelemetriaRepository telemetriaRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final BigDecimal VALOR_DESEJADO = new BigDecimal("15000.00");
    private static final Integer PRAZO = 25;

    @BeforeAll
    static void adicionarProdsESimulacao(
            @Autowired ProdutoRepository produtoRepository,
            @Autowired ObjectMapper objectMapper,
            @Autowired MockMvc mockMvc,
            @Autowired SimulacaoRepository simulacaoRepository,
            @Autowired TelemetriaRepository telemetriaRepository
    ) throws Exception {

        telemetriaRepository.deleteAll();

        if(produtoRepository.findAll().isEmpty()) {
            Produto prod1 = Produto
                    .builder()
                    .coProduto(1)
                    .noProduto("Produto 1")
                    .nuMaximoMeses((short) 24)
                    .pcTaxaJuros(new BigDecimal("0.0179"))
                    .vrMinimo(new BigDecimal(200))
                    .vrMaximo(new BigDecimal(10000))
                    .build();
            Produto prod2 = Produto
                    .builder()
                    .coProduto(2)
                    .noProduto("Produto 2")
                    .nuMaximoMeses((short) 48)
                    .nuMinimoMeses((short) 25)
                    .pcTaxaJuros(new BigDecimal("0.0175"))
                    .vrMinimo(new BigDecimal(10001))
                    .vrMaximo(new BigDecimal(100000))
                    .build();
            Produto prod3 = Produto
                    .builder()
                    .coProduto(3)
                    .noProduto("Produto 3")
                    .nuMaximoMeses((short) 96)
                    .nuMinimoMeses((short) 49)
                    .pcTaxaJuros(new BigDecimal("0.0182"))
                    .vrMinimo(new BigDecimal("100000.01"))
                    .vrMaximo(new BigDecimal(1000000))
                    .build();
            Produto prod4 = Produto
                    .builder()
                    .coProduto(4)
                    .noProduto("Produto 4")
                    .nuMinimoMeses((short) 97)
                    .pcTaxaJuros(new BigDecimal("0.0151"))
                    .vrMinimo(new BigDecimal("1000000.01"))
                    .build();
            produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4));
        }

        SimulacaoRequestDTO req = new SimulacaoRequestDTO(VALOR_DESEJADO, PRAZO);
        String json = objectMapper.writeValueAsString(req);

        mockMvc.perform(post("/simulacao/realizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        mockMvc.perform(get("/simulacao?pagina=1&qtdRegistrosPagina=10&mostrarParcelas=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        mockMvc.perform(get("/simulacao?pagina=1&qtdRegistrosPagina=10&mostrarParcelas=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

    @Test
    void consultarTelemetriaTest() throws Exception {

        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        MvcResult resJson = mockMvc.perform(get("/telemetria?dataReferencia=" + dataAtual)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(resJson);
        ConsultarTelemetriaResponseDTO res = objectMapper.readValue(resJson.getResponse().getContentAsString(), new TypeReference<ApiResponse<ConsultarTelemetriaResponseDTO>>() {}).getResponse();
        assertEquals(dataAtual, res.getDataReferencia());
        assertEquals(2, res.getListaEndpoints().size());
        assertEquals("/simulacao", res.getListaEndpoints().get(0).getNomeApi());
        assertEquals("GET", res.getListaEndpoints().get(0).getMetodoHttp());
        assertEquals(2, res.getListaEndpoints().get(0).getQtdRequisicoes());
        assertEquals(1, res.getListaEndpoints().get(1).getQtdRequisicoes());
    }

}
