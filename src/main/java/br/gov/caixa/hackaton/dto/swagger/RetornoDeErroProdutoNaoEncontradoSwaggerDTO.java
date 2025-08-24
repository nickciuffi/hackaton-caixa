package br.gov.caixa.hackaton.dto.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RetornoDeErroProdutoNaoEncontradoSwaggerDTO {

    @Schema(description = "Lista de mensagens de erro", example = "[\"Produto não encontrado para os critérios informados.\"]")
    private List<String> messages;
}
