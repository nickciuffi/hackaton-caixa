package br.gov.caixa.hackaton.dto.simulacao;

import br.gov.caixa.hackaton.entity.local.Parcela;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ParcelaDTO {
    private Integer numero;
    private BigDecimal valorAmortizacao;
    private BigDecimal valorJuros;
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
