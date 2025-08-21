package br.gov.caixa.hackaton.entity.local;

import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "telemetria")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Telemetria {

    @Id
    @Column(name = "ID_TELEMETRIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idTelemetria;

    @Column(name = "NO_API")
    private String nomeApi;

    @Column(name = "TS_TEMPO_EXECUCAO")
    private Long tempoExecucao;

    @Column(name = "IC_SUCESSO")
    private boolean isSucesso;

    @Column(name = "DT_TELEMETRIA")
    private LocalDate dataTelemetria;

    @Column(name = "NO_METODO_HTTP")
    private String metodoHttp;

    public static Telemetria fromDto(TelemetriaDTO dto){
        return Telemetria
                .builder()
                .nomeApi(dto.getNomeApi())
                .tempoExecucao(dto.getTempoExecucao())
                .isSucesso(dto.isSucesso())
                .dataTelemetria(LocalDate.now())
                .metodoHttp(dto.getMethodoHttp())
                .build();
    }

}
