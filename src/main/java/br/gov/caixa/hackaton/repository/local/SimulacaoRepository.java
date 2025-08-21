package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Integer> {

    @Query(value = "select * from simulacoes s where s.co_produto = ?1 and s.dt_simulacao = ?2", nativeQuery = true)
    public List<Simulacao> findSimulacaoPorDataEProduto(Integer produto, LocalDate data);
}
