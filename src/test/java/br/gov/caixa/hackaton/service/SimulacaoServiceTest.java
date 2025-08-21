package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.SimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdRequestDTO;
import br.gov.caixa.hackaton.entity.local.Simulacao;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.exception.ProdutoNaoEncontradoException;
import br.gov.caixa.hackaton.repository.local.SimulacaoRepository;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import br.gov.caixa.hackaton.service.implementation.SimulacaoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


@SpringBootTest
public class SimulacaoServiceTest {

    @Mock
    private SimulacaoRepository simulacaoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private SimulacaoServiceImpl service;

    private static final Integer COD_PRODUTO = 123;
    private static final Integer ID_SIMULACAO = 12213;
    private static final String NO_PRODUTO = "Produto 1";
    private static final BigDecimal TAXA_JUROS = new BigDecimal("0.0179");
    private static final BigDecimal VALOR_DESEJADO = new BigDecimal("1000.00");
    private static final BigDecimal VALOR_TOTAL_PARCELAS = new BigDecimal("1200.00");
    private static final Integer PRAZO = 10;
    private static final String TIPO_SIMULACAO_SAC = "SAC";
    private static final String TIPO_SIMULACAO_PRICE = "PRICE";
    private static final String DATA_CORRETA = "20-08-2025";
    private static final BigDecimal VALOR_PRESTACAO = new BigDecimal("10000.00");

    private Produto gerarProduto(){
        Produto prodEnt = new Produto();
        prodEnt.setCoProduto(COD_PRODUTO);
        prodEnt.setNoProduto(NO_PRODUTO);
        prodEnt.setPcTaxaJuros(TAXA_JUROS);
        return prodEnt;
    }

    private Simulacao gerarSimulacaoId(){
        Simulacao simulacaoEnt = new Simulacao();
        simulacaoEnt.setIdSimulacao(ID_SIMULACAO);
        return simulacaoEnt;
    }

    private Simulacao gerarSimulacaoPorDataEProd(){
        return Simulacao
                .builder()
                .coProduto(COD_PRODUTO)
                .noProduto(NO_PRODUTO)
                .pcTaxaJuros(TAXA_JUROS)
                .valorMedioPrestacao(VALOR_PRESTACAO)
                .valorDesejado(VALOR_DESEJADO)
                .valorTotalParcelas(VALOR_TOTAL_PARCELAS)
                .tipo(TIPO_SIMULACAO_PRICE)
                .build();
    }

    @Test
    void realizarSimulacaoTest(){

        Produto prodEnt = this.gerarProduto();
        Simulacao simulacaoEnt = this.gerarSimulacaoId();

        SimulacaoRequestDTO req = new SimulacaoRequestDTO();
        req.setPrazo(PRAZO);
        req.setValorDesejado(VALOR_DESEJADO);

        when(produtoRepository.buscarProdutoParaSimulacao(any(), any())).thenReturn(prodEnt);
        when(simulacaoRepository.save(any())).thenReturn(simulacaoEnt);

        SimulacaoResponseDTO res = service.realizarSimulacao(req);

        assertNotNull(res);

        assertEquals(COD_PRODUTO, res.getCodigoProduto());
        assertEquals(TAXA_JUROS, res.getTaxaJuros());
        assertNotNull(res.getResultadosSimulacao());
        assertEquals(2, res.getResultadosSimulacao().size());

        verify(produtoRepository).buscarProdutoParaSimulacao(any(), any());
        verify(simulacaoRepository, times(2)).save(any());
    }

    @Test
    void realizarSimulacaoProdNaoEncontradoTest(){

        SimulacaoRequestDTO req = new SimulacaoRequestDTO();
        req.setPrazo(PRAZO);
        req.setValorDesejado(VALOR_DESEJADO);

        when(produtoRepository.buscarProdutoParaSimulacao(any(), any())).thenReturn(null);
        assertThrows(ProdutoNaoEncontradoException.class, () -> service.realizarSimulacao(req));
    }

    @Test
    void consultarSimulacoesTest(){

        List<Simulacao> simulacoesEnt = new ArrayList<>();
        Simulacao simu1 = Simulacao
                .builder()
                .idSimulacao(ID_SIMULACAO)
                .prazo(PRAZO)
                .valorDesejado(VALOR_DESEJADO)
                .valorTotalParcelas(VALOR_TOTAL_PARCELAS)
                .tipo(TIPO_SIMULACAO_SAC)
                .build();
        Simulacao simu2 = Simulacao
                .builder()
                .idSimulacao(ID_SIMULACAO)
                .prazo(PRAZO)
                .valorDesejado(VALOR_DESEJADO)
                .valorTotalParcelas(VALOR_TOTAL_PARCELAS)
                .tipo(TIPO_SIMULACAO_PRICE)
                .build();
        simulacoesEnt.add(simu1);
        simulacoesEnt.add(simu2);

        when(simulacaoRepository.findAll()).thenReturn(simulacoesEnt);
        List<SimulacaoDTO> res = service.consultarSimulacoes();

        assertNotNull(res);
        assertEquals(2, res.size());
        assertEquals(ID_SIMULACAO, res.get(0).getIdSimulacao());
        assertEquals(VALOR_TOTAL_PARCELAS, res.get(1).getValorTotalParcelas());

        verify(simulacaoRepository).findAll();
    }

    @Test
    void consultarSimulacoesPorDataEProdTest(){
        SimulacaoPorDataEProdRequestDTO req = SimulacaoPorDataEProdRequestDTO
                .builder()
                .dataReferencia(DATA_CORRETA)
                .codigoProduto(COD_PRODUTO)
                .build();
        Simulacao simu1 = this.gerarSimulacaoPorDataEProd();
        Simulacao simu2 = this.gerarSimulacaoPorDataEProd();
        List<Simulacao> simulacoesEnt = new ArrayList<>(Arrays.asList(simu1, simu2));

        when(simulacaoRepository.findSimulacaoPorDataEProduto(any(), any())).thenReturn(simulacoesEnt);

        List<SimulacaoPorDataEProdDTO> res = service.consultarSimulacoesPorDataEProd(req);

        assertNotNull(res);
        assertEquals(2, res.size());
        assertEquals(VALOR_PRESTACAO, res.get(0).getValorMedioPrestacao());
        assertEquals(VALOR_DESEJADO, res.get(1).getValorTotalDesejado());
        assertEquals(VALOR_TOTAL_PARCELAS, res.get(1).getValorTotalCredito());
        assertEquals(TIPO_SIMULACAO_PRICE, res.get(0).getTipoSimulacao());

        verify(simulacaoRepository).findSimulacaoPorDataEProduto(any(), any());

    }
}
