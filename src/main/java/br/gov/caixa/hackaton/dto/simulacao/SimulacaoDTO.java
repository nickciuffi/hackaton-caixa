package br.gov.caixa.hackaton.dto.simulacao;

import br.gov.caixa.hackaton.entity.local.Simulacao;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SimulacaoDTO {

    private Integer idSimulacao;

    private BigDecimal valorDesejado;

    private Integer prazo;

    private BigDecimal valorTotalParcelas;

    private String tipoSimulacao;

    public static SimulacaoDTO fromEntity(Simulacao ent){
        return SimulacaoDTO
                .builder()
                .idSimulacao(ent.getIdSimulacao())
                .prazo(ent.getPrazo())
                .valorDesejado(ent.getValorDesejado())
                .valorTotalParcelas(ent.getValorTotalParcelas())
                .tipoSimulacao(ent.getTipo())
                .build();
    }
}
