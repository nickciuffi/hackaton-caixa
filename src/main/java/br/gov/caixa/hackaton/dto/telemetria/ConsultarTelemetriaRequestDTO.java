package br.gov.caixa.hackaton.dto.telemetria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ConsultarTelemetriaRequestDTO {

    @NotNull(message = "dataReferencia é um campo obrigatório")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data deve estar no formato dd-MM-yyyy")
    @Schema(description = "Data das informações de telemetria a serem consultadas", example = "23-08-2025")
    private String dataReferencia;
}
