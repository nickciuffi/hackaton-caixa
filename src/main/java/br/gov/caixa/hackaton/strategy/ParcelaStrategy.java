package br.gov.caixa.hackaton.strategy;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ParcelaStrategy {
    List<ParcelaDTO> calcularParcelas(BigDecimal valor, int qtdParcelas, BigDecimal taxa);
}
