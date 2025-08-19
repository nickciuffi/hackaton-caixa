package br.gov.caixa.hackaton.dto.simulacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoRequestDTO {

    @NotNull(message = "O campo 'valorDesejado' é obrigatório.")
    private BigDecimal valorDesejado;

    @NotNull(message = "O campo 'prazo' é obrigatório.")
    private Integer prazo;
}
