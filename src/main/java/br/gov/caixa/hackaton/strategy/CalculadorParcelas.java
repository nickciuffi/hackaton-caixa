package br.gov.caixa.hackaton.strategy;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
public class CalculadorParcelas {

    private ParcelaStrategy strategy;

    public CalculadorParcelas(ParcelaStrategy strategy){
        this.strategy = strategy;
    }

    public List<ParcelaDTO> calcular(BigDecimal valor, int qtdParcelas, BigDecimal taxa){
        return strategy.calcularParcelas(valor, qtdParcelas, taxa);
    }
}
