package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SimulacaoPorDataEProdResponseDTO {
    private String dataReferencia;
    private List<SimulacaoPorDataEProdDTO> simulacoes;
}
