package br.gov.caixa.hackaton.dto.simulacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarSimulacoesRequestDTO {
    @NotNull(message = "O campo 'pagina' é obrigatório.")
    @Min(value = 1, message = "O campo 'pagina' deve ser maior que zero.")
    private Integer pagina;

    @NotNull(message = "O campo 'qtdRegistrosPagina' é obrigatório.")
    @Min(value = 1, message = "O campo 'qtdRegistrosPagina' deve ser maior que zero.")
    private Integer qtdRegistrosPagina;
}
