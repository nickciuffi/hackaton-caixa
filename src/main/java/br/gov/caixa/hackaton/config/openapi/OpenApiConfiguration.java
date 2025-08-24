package br.gov.caixa.hackaton.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Simulação de Crédito - Caixa",
                version = "v1.0",
                description = "API para geração de simulações de empréstimos para a população brasileira.\n\n Collection: [Baixar Collection Postman](http://localhost:8080/collection)"
        )
)
public class OpenApiConfiguration {
}
