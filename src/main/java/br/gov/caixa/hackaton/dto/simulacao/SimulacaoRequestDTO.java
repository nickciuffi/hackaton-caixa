package br.gov.caixa.hackaton.dto.simulacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoRequestDTO {

    @Positive(message = "O campo 'valorDesejado' deve ser maior que zero.")
    @NotNull(message = "O campo 'valorDesejado' é obrigatório.")
    private BigDecimal valorDesejado;

    @Positive(message = "O campo 'prazo' deve ser maior que zero.")
    @NotNull(message = "O campo 'prazo' é obrigatório.")
    private Integer prazo;
}
