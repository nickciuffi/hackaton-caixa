package br.gov.caixa.hackaton.dto.simulacao;

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
public class SimulacaoDTO {

    @Schema(description = "Código numérico de identificação da simulação gerada", example = "123123")
    private Long idSimulacao;

    @Schema(description = "Valor desejado na criação da simulação de empréstimo", example = "5000")
    private BigDecimal valorDesejado;

    @Schema(description = "Prazo da simulação de empréstimo", example = "2")
    private Integer prazo;

    @Schema(description = "Valor total das parcelas geradas na simulação", example = "6000")
    private BigDecimal valorTotalParcelas;

    @Schema(description = "Nome do sistema de amortização", example = "PRICE")
    private String tipoSimulacao;

    @Schema(description = "Lista de parcelas geradas para a simulação")
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
