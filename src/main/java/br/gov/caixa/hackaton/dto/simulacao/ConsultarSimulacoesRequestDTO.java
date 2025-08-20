package br.gov.caixa.hackaton.dto.simulacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ConsultarSimulacoesRequestDTO {
    @NotNull(message = "A página é um parâmetro obrigatório")
    @Min(value = 1, message = "A página deve ser maior que zero")
    private Integer pagina;

    @NotNull(message = "A quantidade de registros por página é um parâmetro obrigatório")
    @Min(value = 1, message = "A quantidade de registros por página deve ser maior que zero")
    private Integer qtdRegistrosPagina;
}
