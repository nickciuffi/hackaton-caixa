package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaRequestDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.service.implementation.TelemetriaService;
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
    public ResponseEntity<ApiResponse<ConsultarTelemetriaResponseDTO>> consultarTelemetriaPorData(@Valid @ModelAttribute ConsultarTelemetriaRequestDTO req) {

        ConsultarTelemetriaResponseDTO res = telemetriaService.consultarTelemetriaPorData(req.getDataReferencia());
        return ResponseEntity.ok().body(new ApiResponse<>(res, "Telemetria da data " + req.getDataReferencia() + " consultada com sucesso!"));
    }
}
