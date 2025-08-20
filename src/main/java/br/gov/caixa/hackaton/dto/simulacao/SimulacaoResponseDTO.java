package br.gov.caixa.hackaton.dto.simulacao;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SimulacaoResponseDTO {

    private Integer codigoProduto;

    private String descricaoProduto;

    private BigDecimal taxaJuros;

    private List<ResultadoSimulacaoDTO> resultadosSimulacao;
}
