package br.gov.caixa.hackaton.strategy;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SACStrategy implements ParcelaStrategy{
    @Override
    public List<ParcelaDTO> calcularParcelas(BigDecimal valor, int qtdParcelas, BigDecimal taxa) {

        List<ParcelaDTO> parcelas = new ArrayList<>();

        BigDecimal amortizacao = valor.divide(BigDecimal.valueOf(qtdParcelas), 2, RoundingMode.HALF_UP);

        BigDecimal saldoDevedor = valor;

        for (int i = 1; i <= qtdParcelas; i++) {

            BigDecimal juros = saldoDevedor.multiply(taxa);
            saldoDevedor = valor.subtract(amortizacao.multiply(BigDecimal.valueOf(i)));

            ParcelaDTO parcela = ParcelaDTO
                    .builder()
                    .numero(i)
                    .valorAmortizacao(amortizacao.setScale(2, RoundingMode.HALF_UP))
                    .valorPrestacao(amortizacao.add(juros).setScale(2, RoundingMode.HALF_UP))
                    .valorJuros(juros.setScale(2, RoundingMode.HALF_UP))
                    .build();

            parcelas.add(parcela);
        }

        return parcelas;
    }
}
