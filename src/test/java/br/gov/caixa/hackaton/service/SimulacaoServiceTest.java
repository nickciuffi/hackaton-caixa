package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.repository.local.SimulacaoRepository;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SimulacaoServiceTest {

    @Mock
    private SimulacaoRepository simulacaoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private SimulacaoService service;

    private static final Integer COD_PRODUTO = 123;
    private static final String NO_PRODUTO = "Produto 1";
    private static final BigDecimal TAXA_JUROS = new BigDecimal("0.0179");

    @Test
    void realizarSimulacaoTest(){
        Produto prodEnt = new Produto();
        prodEnt.setCoProduto(COD_PRODUTO);
        prodEnt.setNoProduto(NO_PRODUTO);
        prodEnt.setPcTaxaJuros(TAXA_JUROS);

        SimulacaoRequestDTO req = new SimulacaoRequestDTO();
        req.setPrazo(10);
        req.setValorDesejado(new BigDecimal("1000.00"));

        when(produtoRepository.buscarProdutoParaSimulacao(any(), any())).thenReturn(prodEnt);
        when(simulacaoRepository.save(any())).thenReturn(prodEnt);

        SimulacaoResponseDTO res = service.realizarSimulacao(req);


    }
}
