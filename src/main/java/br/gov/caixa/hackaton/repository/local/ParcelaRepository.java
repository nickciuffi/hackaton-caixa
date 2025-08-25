package br.gov.caixa.hackaton.repository.local;

import br.gov.caixa.hackaton.entity.local.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
}
