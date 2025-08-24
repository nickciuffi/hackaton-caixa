package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Telemetria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TelemetriaRepository extends JpaRepository<Telemetria, Integer> {

    @Query(value = "select t.nomeApi as nomeApi, t.metodoHttp as metodoHttp, COUNT(t.idTelemetria) as qtdRequisicoes, ROUND(AVG(t.tempoExecucao), 2) as tempoMedio, MIN(t.tempoExecucao) as tempoMinimo, MAX(t.tempoExecucao) as tempoMaximo, (SUM(t.isSucesso) / COUNT(t.idTelemetria)) as percentualSucesso from Telemetria t where t.dataTelemetria = ?1 group by t.nomeApi, t.metodoHttp order by qtdRequisicoes desc")
    public List<Object[]> consultarTelemetriaPorData(LocalDate data);
}
