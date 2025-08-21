package br.gov.caixa.hackaton.service.implementation;

import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;
import br.gov.caixa.hackaton.entity.local.Telemetria;
import br.gov.caixa.hackaton.repository.local.TelemetriaRepository;
import br.gov.caixa.hackaton.service.TelemetriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TelemetriaServiceImpl implements TelemetriaService {

    private TelemetriaRepository telemetriaRepositoty;

    public void cadastrarTelemetria(TelemetriaDTO req){

        Telemetria telEnt = Telemetria.fromDto(req);
        telemetriaRepositoty.save(telEnt);
    }

    public ConsultarTelemetriaResponseDTO consultarTelemetriaPorData(String data){

        LocalDate dataReq = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Object[]> resObj = telemetriaRepositoty.consultarTelemetriaPorData(dataReq);
        List<ConsultarTelemetriaDTO> res = new ArrayList<>();

        resObj.forEach(obj -> {
            res.add(ConsultarTelemetriaDTO.fromEntity(obj));
        });;

        return ConsultarTelemetriaResponseDTO.builder().dataReferencia(data).listaEndpoints(res).build();
    }
}
