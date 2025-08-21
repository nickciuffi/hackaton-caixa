package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaDTO;
import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;
import br.gov.caixa.hackaton.entity.local.Telemetria;
import br.gov.caixa.hackaton.repository.local.TelemetriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TelemetriaService {

    private TelemetriaRepository telemetriaRepositoty;

    public void cadastrarTelemetria(TelemetriaDTO req){
        telemetriaRepositoty.save(Telemetria
                .builder()
                .nomeApi(req.getNomeApi())
                .tempoExecucao(req.getTempoExecucao())
                .isSucesso(req.isSucesso())
                .dataTelemetria(LocalDate.now())
                .metodoHttp(req.getMethodoHttp())
                .build());
    }

    public ConsultarTelemetriaResponseDTO consultarTelemetriaPorData(String data){

        LocalDate dataReq = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Object[]> resObj = telemetriaRepositoty.consultarTelemetriaPorData(dataReq);
        List<ConsultarTelemetriaDTO> res = new ArrayList<>();

        resObj.stream().forEach(obj -> {
            BigDecimal percentualSucesso = (BigDecimal) obj[6];
            res.add(ConsultarTelemetriaDTO
                    .builder()
                    .nomeApi((String) obj[0])
                    .metodoHttp((String) obj[1])
                    .qtdRequisicoes((Long) obj[2])
                    .tempoMedio((BigDecimal) obj[3])
                    .tempoMinimo((Long) obj[4])
                    .tempoMaximo((Long) obj[5])
                    .percentualSucesso(percentualSucesso.setScale(2, RoundingMode.UP))
                    .build());
        });;

        return ConsultarTelemetriaResponseDTO.builder().dataReferencia(data).listaEndpoints(res).build();
    }
}
