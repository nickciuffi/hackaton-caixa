package br.gov.caixa.hackaton.dto.simulacao;

import br.gov.caixa.hackaton.entity.local.Parcela;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ParcelaDTO {

    @Schema(description = "Numero da parcela calculada para a simulação", example = "1")
    private Integer numero;

    @Schema(description = "Valor da amortização da parcela calculada para a simulação", example = "2500")
    private BigDecimal valorAmortizacao;

    @Schema(description = "Valor do juros da parcela calculada para a simulação", example = "500")
    private BigDecimal valorJuros;

    @Schema(description = "Valor da prestação da parcela calculada para a simulação", example = "3000")
    private BigDecimal valorPrestacao;

    public static List<ParcelaDTO> fromEntitys(List<Parcela> ents){
        List<ParcelaDTO> parcelas = new ArrayList<>();
        for(Parcela ent : ents){
            parcelas.add(ParcelaDTO
                    .builder()
                    .numero(ent.getNumeroParcela())
                    .valorJuros(ent.getValorJuros())
                    .valorPrestacao(ent.getValorPrestacao())
                    .valorAmortizacao(ent.getValorAmortizacao())
                    .build());
        }
        return parcelas;
    }
}
