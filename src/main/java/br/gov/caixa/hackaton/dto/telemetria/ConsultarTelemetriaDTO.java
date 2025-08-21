package br.gov.caixa.hackaton.dto.telemetria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@Builder
public class ConsultarTelemetriaDTO {

    private String nomeApi;

    private String metodoHttp;

    private Long qtdRequisicoes;

    private BigDecimal tempoMedio;

    private Long tempoMinimo;

    private Long tempoMaximo;

    private BigDecimal percentualSucesso;

    public static ConsultarTelemetriaDTO fromEntity(Object[] ent){
        BigDecimal percentualSucesso = (BigDecimal) ent[6];
        return ConsultarTelemetriaDTO
                .builder()
                .nomeApi((String) ent[0])
                .metodoHttp((String) ent[1])
                .qtdRequisicoes((Long) ent[2])
                .tempoMedio((BigDecimal) ent[3])
                .tempoMinimo((Long) ent[4])
                .tempoMaximo((Long) ent[5])
                .percentualSucesso(percentualSucesso.setScale(2, RoundingMode.UP))
                .build();
    }
}
