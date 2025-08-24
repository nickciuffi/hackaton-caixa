package br.gov.caixa.hackaton.dto.telemetria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultarTelemetriaResponseDTO {

    @Schema(description = "Data das informações de telemetria consultadas", example = "23-08-2025")
    private String dataReferencia;

    private List<ConsultarTelemetriaDTO> listaEndpoints;
}
