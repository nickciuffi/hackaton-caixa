package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Telemetria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TelemetriaRepository extends JpaRepository<Telemetria, Integer> {

    @Query(value = "select t.no_api as nomeApi, t.no_metodo_http as metodoHttp, COUNT(t.id_telemetria) as qtdRequisicoes, ROUND(AVG(t.ts_tempo_execucao), 2) as tempoMedio, MIN(t.ts_tempo_execucao) as tempoMinimo, MAX(t.ts_tempo_execucao) as tempoMaximo, (SUM(t.ic_sucesso) / COUNT(t.id_telemetria)) as percentualSucesso from telemetria t where t.dt_telemetria = ?1 group by t.no_api, t.no_metodo_http order by qtdRequisicoes desc", nativeQuery = true)
    public List<Object[]> consultarTelemetriaPorData(LocalDate data);
}
