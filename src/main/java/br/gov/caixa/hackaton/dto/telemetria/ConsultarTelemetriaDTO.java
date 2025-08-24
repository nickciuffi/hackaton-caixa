package br.gov.caixa.hackaton.dto.telemetria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@Builder
public class ConsultarTelemetriaDTO {

    @Schema(description = "Uri da requisição feita", example = "/simulacao/realizar")
    private String nomeApi;

    @Schema(description = "Método HTTP utilizado na requisição feita", example = "POST")
    private String metodoHttp;

    @Schema(description = "Quantidade de requisições feitas no endpoint", example = "20")
    private Long qtdRequisicoes;

    @Schema(description = "Tempo médio de resposta do endpoint", example = "1000")
    private BigDecimal tempoMedio;

    @Schema(description = "Tempo mínimo de resposta do endpoint", example = "100")
    private Long tempoMinimo;

    @Schema(description = "Tempo máximo de resposta do endpoint", example = "2000")
    private Long tempoMaximo;

    @Schema(description = "Taxa de sucesso nas resposta do endpoint", example = "0.6")
    private BigDecimal percentualSucesso;

    public static ConsultarTelemetriaDTO fromEntity(Object[] ent){
        BigDecimal percentualSucesso = (BigDecimal) ent[6];
        return ConsultarTelemetriaDTO
                .builder()
                .nomeApi((String) ent[0])
                .metodoHttp((String) ent[1])
                .qtdRequisicoes((Long) ent[2])
                .tempoMedio(new BigDecimal(String.valueOf(ent[3])))
                .tempoMinimo((Long) ent[4])
                .tempoMaximo((Long) ent[5])
                .percentualSucesso(percentualSucesso.setScale(2, RoundingMode.UP))
                .build();
    }
}
