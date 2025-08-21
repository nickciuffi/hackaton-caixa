package br.gov.caixa.hackaton.unitarios.strategy;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;
import br.gov.caixa.hackaton.strategy.CalculadorParcelas;
import br.gov.caixa.hackaton.strategy.PRICEStrategy;
import br.gov.caixa.hackaton.strategy.SACStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadorParcelasTest {

    @Test
    void calcularComStrategySACTest(){
        CalculadorParcelas calculadorParcelasSAC = new CalculadorParcelas(new SACStrategy());
        List<ParcelaDTO> parcelasCalculadas = calculadorParcelasSAC.calcular(new BigDecimal(1000), 10, new BigDecimal("0.0179"));
        assertEquals(new BigDecimal("117.90"), parcelasCalculadas.get(0).getValorPrestacao());
        assertEquals(new BigDecimal("108.95"), parcelasCalculadas.get(5).getValorPrestacao());
        assertEquals(new BigDecimal("10.74"), parcelasCalculadas.get(4).getValorJuros());
    }

    @Test
    void calcularComStrategyPRICETest(){
        CalculadorParcelas calculadorParcelasSAC = new CalculadorParcelas(new PRICEStrategy());
        List<ParcelaDTO> parcelasCalculadas = calculadorParcelasSAC.calcular(new BigDecimal(1000), 10, new BigDecimal("0.0179"));
        assertEquals(new BigDecimal("110.11"), parcelasCalculadas.get(0).getValorPrestacao());
        assertEquals(new BigDecimal("110.11"), parcelasCalculadas.get(5).getValorPrestacao());
        assertEquals(new BigDecimal("11.12"), parcelasCalculadas.get(4).getValorJuros());
        assertEquals(new BigDecimal("95.54"), parcelasCalculadas.get(2).getValorAmortizacao());
    }
}
