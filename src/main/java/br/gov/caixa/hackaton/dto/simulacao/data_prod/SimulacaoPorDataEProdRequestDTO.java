package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulacaoPorDataEProdRequestDTO {

    @NotNull(message = "dataReferencia é um campo obrigatório")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data deve estar no formato dd-MM-yyyy")
    private String dataReferencia;

    @NotNull(message = "O campo codigoProduto é um campo obrigatório")
    private Integer codigoProduto;
}
