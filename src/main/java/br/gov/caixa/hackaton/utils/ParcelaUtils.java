package br.gov.caixa.hackaton.utils;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;

import java.math.BigDecimal;
import java.util.List;

public class ParcelaUtils {

    public static BigDecimal calcularTotalParcelas(List<ParcelaDTO> parcelas){

        BigDecimal total = new BigDecimal(0);
        for (ParcelaDTO parcela : parcelas) {
            total = total.add(parcela.getValorPrestacao());
        }
        return total;
    }
}
