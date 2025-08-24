package br.gov.caixa.hackaton.dto.telemetria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelemetriaDTO {

    private String nomeApi;

    private Long tempoExecucao;

    private boolean isSucesso;

    private String methodoHttp;
}
