package br.gov.caixa.hackaton.dto.simulacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultadoSimulacaoDTO {
    @NonNull
    @Schema(description = "Nome do sistema de amortização", example = "SAC")
    private String tipo;

    @Schema(description = "Código numérico de identificação da simulação gerada", example = "123")
    private Long idSimulacao;

    @NonNull
    @Schema(description = "Lista de parcelas geradas para a simulação")
    private List<ParcelaDTO> parcelas;
}
