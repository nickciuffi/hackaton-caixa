package br.gov.caixa.hackaton.integracao.simulacao;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
 class RealizarSimulacaoIntegracaoTest {

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

    private static final BigDecimal VALOR_DESEJADO = new BigDecimal("15000");
    private static final Integer PRAZO = 25;

    @BeforeAll
    static void adicionarProds(@Autowired ProdutoRepository produtoRepository){
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

    @Test
    void realizarSimulacaoIntTest() throws Exception {
        /*
         A aplicação deve responder com duas simulações de emprestimo, SAC e PRICE.
         As duas simulações e as suas parcelas devem ser salvas no banco de dados
         A telemetria deve ser capturada e salva no banco de dados
         */

        int quantidadeTelemetria = telemetriaRepository.findAll().size();
        int quantidadeSimulacoes = simulacaoRepository.findAll().size();
        int quantidadeParcelas = parcelaRepository.findAll().size();

        SimulacaoRequestDTO req = new SimulacaoRequestDTO(VALOR_DESEJADO, PRAZO);
        String json = objectMapper.writeValueAsString(req);

        MvcResult resJson = mockMvc.perform(post("/simulacao/realizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(resJson);
        ApiResponse<SimulacaoResponseDTO> res = objectMapper.readValue(resJson.getResponse().getContentAsString(), new TypeReference<ApiResponse<SimulacaoResponseDTO>>() {});

        assertNotNull(res);
        assertEquals(2, res.getResponse().getResultadosSimulacao().size());
        assertEquals(2, res.getResponse().getCodigoProduto());
        assertEquals(new BigDecimal("600.00"), res.getResponse().getResultadosSimulacao().get(0).getParcelas().get(3).getValorAmortizacao());
        assertEquals(new BigDecimal("262.50"), res.getResponse().getResultadosSimulacao().get(1).getParcelas().get(0).getValorJuros());


        assertEquals(quantidadeSimulacoes + 2, simulacaoRepository.findAll().size());
        assertEquals(quantidadeTelemetria + 1, telemetriaRepository.findAll().size());
        assertEquals(quantidadeParcelas + (PRAZO * 2), parcelaRepository.findAll().size());

    }

}
