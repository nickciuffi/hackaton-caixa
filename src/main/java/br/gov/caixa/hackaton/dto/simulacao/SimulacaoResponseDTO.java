package br.gov.caixa.hackaton.dto.simulacao;

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

    private Integer codigoProduto;

    private String descricaoProduto;

    private BigDecimal taxaJuros;

    private List<ResultadoSimulacaoDTO> resultadosSimulacao;
}
