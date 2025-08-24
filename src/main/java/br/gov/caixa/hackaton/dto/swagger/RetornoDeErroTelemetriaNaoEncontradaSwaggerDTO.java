package br.gov.caixa.hackaton.dto.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RetornoDeErroTelemetriaNaoEncontradaSwaggerDTO {

    @Schema(description = "Lista de mensagens de erro", example = "[\"Nenhuma informação de telemetria encontrada na data: 23-08-2025\"]")
    private List<String> messages;
}
