package br.gov.caixa.hackaton.dto.simulacao;

import br.gov.caixa.hackaton.entity.local.Simulacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulacaoDTO {

    private Long idSimulacao;

    private BigDecimal valorDesejado;

    private Integer prazo;

    private BigDecimal valorTotalParcelas;

    private String tipoSimulacao;

    private List<ParcelaDTO> parcelas;

    public static SimulacaoDTO fromEntity(Simulacao ent){
       return SimulacaoDTO
                .builder()
                .idSimulacao(ent.getIdSimulacao())
                .prazo(ent.getPrazo())
                .valorDesejado(ent.getValorDesejado())
                .valorTotalParcelas(ent.getValorTotalParcelas())
                .tipoSimulacao(ent.getTipo())
                .parcelas(ent.getParcelas() != null ? ParcelaDTO.fromEntitys(ent.getParcelas()) : null)
                .build();
    }
}
