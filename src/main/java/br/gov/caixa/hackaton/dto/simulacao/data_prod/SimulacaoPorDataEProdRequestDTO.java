package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Data das simulações a serem consultadas", example = "23-08-2025")
    private String dataReferencia;

    @NotNull(message = "O campo codigoProduto é um campo obrigatório")
    @Schema(description = "Código do produto as simulações a serem consultadas", example = "1")
    private Integer codigoProduto;


    @NotNull(message = "O campo 'mostrarParcelas' é obrigatório.")
    @Max(value = 1, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    @Min(value = 0, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    @Schema(description = "Configuração do retorno do endpoint, para que as parcelas sejam retornadas, o valor deve ser 1 e para que elas não sejam retornadas, o valor deve ser 0", example = "1")
    private Integer mostrarParcelas;
}
