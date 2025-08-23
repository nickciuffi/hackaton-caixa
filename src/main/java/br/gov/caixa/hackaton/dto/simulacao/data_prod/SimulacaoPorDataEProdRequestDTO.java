package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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


    @NotNull(message = "O campo 'mostrarParcelas' é obrigatório.")
    @Max(value = 1, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    @Min(value = 0, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    private Integer mostrarParcelas;
}
