package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.swagger.RetornoDeErroNosParametrosSwaggerDTO;
import br.gov.caixa.hackaton.dto.swagger.RetornoDeErroTelemetriaNaoEncontradaSwaggerDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaRequestDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.service.TelemetriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telemetria")
@AllArgsConstructor
public class TelemetriaController {

    private TelemetriaService telemetriaService;

    @GetMapping("")
    @Operation(summary = "Consultar telemetria", description = "Consultar dados de telemetria sobre as consultas de todos os endpoints da aplicação")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro nos parâmetros", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroNosParametrosSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma informação de telemetria encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroTelemetriaNaoEncontradaSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Informações de telemetria consultadas com sucesso")
    })
    public ResponseEntity<ApiResponse<ConsultarTelemetriaResponseDTO>> consultarTelemetriaPorData(@Valid @ModelAttribute ConsultarTelemetriaRequestDTO req) {

        ConsultarTelemetriaResponseDTO res = telemetriaService.consultarTelemetriaPorData(req.getDataReferencia());
        return ResponseEntity.ok().body(new ApiResponse<>(res, "Telemetria da data " + req.getDataReferencia() + " consultada com sucesso!"));
    }
}
