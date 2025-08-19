package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.entity.Produto;
import br.gov.caixa.hackaton.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SimulacaoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public SimulacaoResponseDTO realizarSimulacao(SimulacaoRequestDTO req){

        Produto prodAdequado = produtoRepository.BuscarProdutoParaSimulacao(req.getPrazo(), req.getValorDesejado());

        SimulacaoResponseDTO res = SimulacaoResponseDTO
                .builder()
                .idSimulacao(123)
                .resultadosSimulacao(new ArrayList<>())
                .codigoProduto(prodAdequado.getCoProduto())
                .descricaoProduto(prodAdequado.getNoProduto())
                .taxaJuros(prodAdequado.getPcTaxaJuros())
                .build();

        return res;
    }
}
