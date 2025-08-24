package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimulacaoPorDataEProdResponseDTO {

    @Schema(description = "Data das simulações a consultadas", example = "23-08-2025")
    private String dataReferencia;

    private List<SimulacaoPorDataEProdDTO> simulacoes;
}
