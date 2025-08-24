package br.gov.caixa.hackaton.dto.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RetornoDeErroNosParametrosSwaggerDTO {

    @Schema(description = "Lista de mensagens de erro", example = "[\"Existem erros nos parâmetros enviados!\", \"pagina: O campo 'xxxxx' é obrigatório.\"]")
    private List<String> messages;
}
