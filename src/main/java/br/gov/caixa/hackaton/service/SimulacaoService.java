package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.*;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdDTO;
import br.gov.caixa.hackaton.entity.local.Simulacao;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.exception.NenhumaSimulacaoEncontradaException;
import br.gov.caixa.hackaton.exception.ProdutoNaoEncontradoException;
import br.gov.caixa.hackaton.repository.local.SimulacaoRepository;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import br.gov.caixa.hackaton.strategy.CalculadorParcelas;
import br.gov.caixa.hackaton.strategy.PRICEStrategy;
import br.gov.caixa.hackaton.strategy.SACStrategy;
import br.gov.caixa.hackaton.utils.ParcelaUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SimulacaoService {

    private ProdutoRepository produtoRepository;

    private SimulacaoRepository simulacaoRepository;

    public SimulacaoResponseDTO realizarSimulacao(SimulacaoRequestDTO req){

        Produto prodAdequado = produtoRepository.buscarProdutoParaSimulacao(req.getPrazo(), req.getValorDesejado());

        if(prodAdequado == null) {
            throw new ProdutoNaoEncontradoException();
        }

        List<ResultadoSimulacaoDTO> resultados = this.cadastrarSimulacoes(
                this.gerarResultados(req.getValorDesejado(), req.getPrazo(), prodAdequado.getPcTaxaJuros()),
                req,
                prodAdequado
        );

        return SimulacaoResponseDTO
                .builder()
                .resultadosSimulacao(resultados)
                .codigoProduto(prodAdequado.getCoProduto())
                .descricaoProduto(prodAdequado.getNoProduto())
                .taxaJuros(prodAdequado.getPcTaxaJuros())
                .build();
    }

    public List<SimulacaoDTO> consultarSimulacoes(){
        List<Simulacao> simulacoes = simulacaoRepository.findAll();
        List<SimulacaoDTO> dtos = new ArrayList<>();
        for(Simulacao simulacaoEnt : simulacoes){
            dtos.add(SimulacaoDTO.fromEntity(simulacaoEnt));
        }
        return dtos;
    }

    public List<SimulacaoPorDataEProdDTO> consultarSimulacoesPorDataEProd(SimulacaoPorDataEProdRequestDTO req){
        LocalDate dataReq = LocalDate.parse(req.getDataReferencia(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Simulacao> simulacoesEnt = simulacaoRepository.findSimulacaoPorDataEProduto(req.getCodigoProduto(), dataReq);

        if(simulacoesEnt.isEmpty()) throw new NenhumaSimulacaoEncontradaException();

        List<SimulacaoPorDataEProdDTO> dtos = new ArrayList<>();
        for(Simulacao simulacaoEnt : simulacoesEnt){
            dtos.add(SimulacaoPorDataEProdDTO.fromEntity(simulacaoEnt));
        }

        return dtos;
    }

    private List<ResultadoSimulacaoDTO> cadastrarSimulacoes(List<ResultadoSimulacaoDTO> simulacoes, SimulacaoRequestDTO req, Produto prod){
        for(ResultadoSimulacaoDTO simulacao : simulacoes){

            Simulacao simulacaoEnt = new Simulacao(
                    req.getValorDesejado(), req.getPrazo(), ParcelaUtils.calcularTotalParcelas(simulacao.getParcelas()),
                    simulacao.getTipo(), LocalDate.now(), prod.getCoProduto(), prod.getNoProduto(),
                    ParcelaUtils.calcularMediaPrestacao(simulacao.getParcelas()), prod.getPcTaxaJuros());

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
