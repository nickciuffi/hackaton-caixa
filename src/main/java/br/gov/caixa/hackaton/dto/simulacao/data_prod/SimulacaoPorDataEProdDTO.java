package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;
import br.gov.caixa.hackaton.entity.local.Simulacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulacaoPorDataEProdDTO {

    @Schema(description = "Código do produto utilizado na simulação", example = "1")
    private Integer codigoProduto;

    @Schema(description = "Descrição do produto utilizado nas simulação", example = "Produto 1")
    private String descricaoProduto;

    @Schema(description = "Taxa média de juros do produto utilizado na simulação", example = "0.1")
    private BigDecimal taxaMediaJuro;

    @Schema(description = "Valor medio das prestações da simulação", example = "3000")
    private BigDecimal valorMedioPrestacao;

    @Schema(description = "Valor total desejado na simulação", example = "5000")
    private BigDecimal valorTotalDesejado;

    @Schema(description = "Valor total desejado na simulação", example = "6000")
    private BigDecimal valorTotalCredito;

    private String tipoSimulacao;

    private List<ParcelaDTO> parcelas;

    public static SimulacaoPorDataEProdDTO fromEntity(Simulacao ent){
        return SimulacaoPorDataEProdDTO
                .builder()
                .codigoProduto(ent.getCoProduto())
                .descricaoProduto(ent.getNoProduto())
                .taxaMediaJuro(ent.getPcTaxaJuros())
                .valorMedioPrestacao(ent.getValorMedioPrestacao())
                .valorTotalDesejado(ent.getValorDesejado())
                .valorTotalCredito(ent.getValorTotalParcelas())
                .tipoSimulacao(ent.getTipo())
                .parcelas(ent.getParcelas() != null ? ParcelaDTO.fromEntitys(ent.getParcelas()) : null)
                .build();
    }
}
