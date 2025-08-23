package br.gov.caixa.hackaton.dto.simulacao;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultadoSimulacaoDTO {
    @NonNull
    private String tipo;
    private Long idSimulacao;
    @NonNull
    private List<ParcelaDTO> parcelas;
}
