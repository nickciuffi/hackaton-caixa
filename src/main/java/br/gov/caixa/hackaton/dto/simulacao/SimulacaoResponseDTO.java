package br.gov.caixa.hackaton.dto.simulacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulacaoResponseDTO {

    @Schema(description = "Código do produto encontrado para os parâmetros enviados", example = "1")
    private Integer codigoProduto;

    @Schema(description = "Descrição do produto encontrado para os parâmetros enviados", example = "Produto 1")
    private String descricaoProduto;

    @Schema(description = "Taxa de juros mensal do produto encontrado para os parâmetros enviados", example = "0.01")
    private BigDecimal taxaJuros;

    @Schema(description = "Resultados da simulação nos sistemas de amortização SAC e PRICE")
    private List<ResultadoSimulacaoDTO> resultadosSimulacao;
}
