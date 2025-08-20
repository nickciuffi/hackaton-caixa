package br.gov.caixa.hackaton.utils;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParcelaUtilsTest {
    @Test
    void calcularTotalParcelasTest(){
        ParcelaDTO p1 = ParcelaDTO
                .builder()
                .numero(1)
                .valorAmortizacao(new BigDecimal(50))
                .valorPrestacao(new BigDecimal(500))
                .build();
        ParcelaDTO p2 = ParcelaDTO
                .builder()
                .numero(2)
                .valorAmortizacao(new BigDecimal(100))
                .valorPrestacao(new BigDecimal(1000))
                .build();
        List<ParcelaDTO> parcelas = new ArrayList<>(Arrays.asList(p1, p1, p2));
        BigDecimal valorTotal = ParcelaUtils.calcularTotalParcelas(parcelas);
        assertEquals(new BigDecimal(2000), valorTotal);
    }

    @Test
    void calcularTotalParcelasListVaziaTest(){
        List<ParcelaDTO> parcelas = new ArrayList<>();
        BigDecimal valorTotal = ParcelaUtils.calcularTotalParcelas(parcelas);
        assertEquals(new BigDecimal(0), valorTotal);
    }
}
