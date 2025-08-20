package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Integer> {}
