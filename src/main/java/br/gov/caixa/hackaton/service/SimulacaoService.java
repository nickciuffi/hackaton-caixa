package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.ResultadoSimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.entity.local.Simulacao;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.exception.ProdutoNaoEncontradoException;
import br.gov.caixa.hackaton.repository.local.SimulacaoRepository;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import br.gov.caixa.hackaton.strategy.CalculadorParcelas;
import br.gov.caixa.hackaton.strategy.PRICEStrategy;
import br.gov.caixa.hackaton.strategy.ParcelaStrategy;
import br.gov.caixa.hackaton.strategy.SACStrategy;
import br.gov.caixa.hackaton.utils.ParcelaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimulacaoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SimulacaoRepository simulacaoRepository;

    public SimulacaoResponseDTO realizarSimulacao(SimulacaoRequestDTO req){

        Produto prodAdequado = produtoRepository.BuscarProdutoParaSimulacao(req.getPrazo(), req.getValorDesejado());

        if(prodAdequado == null) {
            throw new ProdutoNaoEncontradoException();
        }

        List<ResultadoSimulacaoDTO> resultados = this.cadastrarSimulacoes(
                this.gerarResultados(req.getValorDesejado(), req.getPrazo(), prodAdequado.getPcTaxaJuros()),
                req
        );

        SimulacaoResponseDTO res = SimulacaoResponseDTO
                .builder()
                .resultadosSimulacao(resultados)
                .codigoProduto(prodAdequado.getCoProduto())
                .descricaoProduto(prodAdequado.getNoProduto())
                .taxaJuros(prodAdequado.getPcTaxaJuros())
                .build();

        return res;
    }

    private List<ResultadoSimulacaoDTO> cadastrarSimulacoes(List<ResultadoSimulacaoDTO> simulacoes, SimulacaoRequestDTO req){
        for(ResultadoSimulacaoDTO simulacao : simulacoes){
            Simulacao simulacaoEnt = new Simulacao(req.getValorDesejado(), req.getPrazo(), ParcelaUtils.calcularTotalParcelas(simulacao.getParcelas()), simulacao.getTipo());
            Simulacao simulacaoSalva = simulacaoRepository.save(simulacaoEnt);
            simulacao.setIdSimulacao(simulacaoSalva.getIdSimulacao());
        }
        return simulacoes;
    }

    private List<ResultadoSimulacaoDTO> gerarResultados(BigDecimal valor, int qtdParcelas, BigDecimal taxa){

        List<ResultadoSimulacaoDTO> resultados = new ArrayList<>();
        CalculadorParcelas calculadorSAC = new CalculadorParcelas(new SACStrategy());
        CalculadorParcelas calculadoraPRICE = new CalculadorParcelas(new PRICEStrategy());

        ResultadoSimulacaoDTO resSAC = new ResultadoSimulacaoDTO("SAC", calculadorSAC.calcular(valor, qtdParcelas, taxa));
        ResultadoSimulacaoDTO resPRICE = new ResultadoSimulacaoDTO("PRICE", calculadoraPRICE.calcular(valor, qtdParcelas, taxa));

        resultados.add(resSAC);
        resultados.add(resPRICE);

        return resultados;
    }


}
