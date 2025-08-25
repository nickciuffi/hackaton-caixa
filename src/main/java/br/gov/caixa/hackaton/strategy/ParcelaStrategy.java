package br.gov.caixa.hackaton.strategy;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;

import java.math.BigDecimal;
import java.util.List;

/*
    Utilização do padrão de projeto Strategy, que ajuda a tornar a funcionalidade de calcular parcelas aberta para extensão e fechada para alteração (Open-Closed principle)
 */

public interface ParcelaStrategy {
    List<ParcelaDTO> calcularParcelas(BigDecimal valor, int qtdParcelas, BigDecimal taxa);
}
