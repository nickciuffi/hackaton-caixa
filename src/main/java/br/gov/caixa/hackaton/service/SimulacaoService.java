package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.simulacao.ResultadoSimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdRequestDTO;
import br.gov.caixa.hackaton.entity.remote.Produto;

import java.util.List;

public interface SimulacaoService {

    public SimulacaoResponseDTO realizarSimulacao(SimulacaoRequestDTO req);

    public List<SimulacaoDTO> consultarSimulacoes();

    public List<SimulacaoPorDataEProdDTO> consultarSimulacoesPorDataEProd(SimulacaoPorDataEProdRequestDTO req);

}
