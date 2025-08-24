package br.gov.caixa.hackaton.dto.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RetornoDeErroSimulacaoNaoEncontradaSwaggerDTO {

    @Schema(description = "Lista de mensagens de erro", example = "[\"Nenhuma simulação encontrada!\"]")
    private List<String> messages;
}
