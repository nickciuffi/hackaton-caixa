package br.gov.caixa.hackaton.utils;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ParcelaUtils {

    private ParcelaUtils(){}

    public static BigDecimal calcularTotalParcelas(List<ParcelaDTO> parcelas){

        BigDecimal total = new BigDecimal(0);
        for (ParcelaDTO parcela : parcelas) {
            total = total.add(parcela.getValorPrestacao());
        }
        return total;
    }

    public static BigDecimal calcularMediaPrestacao(List<ParcelaDTO> parcelas){
        BigDecimal total = ParcelaUtils.calcularTotalParcelas(parcelas);
        return total.divide(BigDecimal.valueOf(parcelas.size()), 2, RoundingMode.HALF_UP);
    }
}
