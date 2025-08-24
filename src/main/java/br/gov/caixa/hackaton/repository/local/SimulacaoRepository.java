package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Integer> {

    @Query(value = "select s from Simulacao s where s.coProduto = ?1 and s.dataSimulacao = ?2")
    public List<Simulacao> findSimulacaoPorDataEProduto(Integer produto, LocalDate data);
}
