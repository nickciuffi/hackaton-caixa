package br.gov.caixa.hackaton.dto.telemetria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
}
