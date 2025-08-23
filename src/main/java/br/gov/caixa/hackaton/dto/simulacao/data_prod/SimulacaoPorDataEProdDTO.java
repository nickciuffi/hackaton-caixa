package br.gov.caixa.hackaton.dto.simulacao.data_prod;

import br.gov.caixa.hackaton.dto.simulacao.ParcelaDTO;
import br.gov.caixa.hackaton.entity.local.Simulacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulacaoPorDataEProdDTO {

    private Integer codigoProduto;

    private String descricaoProduto;

    private BigDecimal taxaMediaJuro;

    private BigDecimal valorMedioPrestacao;

    private BigDecimal valorTotalDesejado;

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
