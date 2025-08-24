package br.gov.caixa.hackaton.dto.simulacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
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
    @Schema(description = "Página a ser consultada na paginação, começando na página 1", example = "1")
    private Integer pagina;

    @NotNull(message = "O campo 'qtdRegistrosPagina' é obrigatório.")
    @Min(value = 1, message = "O campo 'qtdRegistrosPagina' deve ser maior que zero.")
    @Schema(description = "Quantidade de registros a serem retornados por página", example = "10")
    private Integer qtdRegistrosPagina;

    @NotNull(message = "O campo 'mostrarParcelas' é obrigatório.")
    @Max(value = 1, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    @Min(value = 0, message = "O campo 'mostrarParcelas' deve ter o valor 0 ou 1")
    @Schema(description = "Configuração do retorno do endpoint, para que as parcelas sejam retornadas, o valor deve ser 1 e para que elas não sejam retornadas, o valor deve ser 0", example = "1")
    private Integer mostrarParcelas;
}
