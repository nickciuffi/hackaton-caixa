package br.gov.caixa.hackaton.dto.telemetria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultarTelemetriaResponseDTO {

    private String dataReferencia;

    private List<ConsultarTelemetriaDTO> listaEndpoints;
}
