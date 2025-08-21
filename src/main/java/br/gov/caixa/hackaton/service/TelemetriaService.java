package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;

public interface TelemetriaService {

    public void cadastrarTelemetria(TelemetriaDTO req);

    public ConsultarTelemetriaResponseDTO consultarTelemetriaPorData(String data);
}
