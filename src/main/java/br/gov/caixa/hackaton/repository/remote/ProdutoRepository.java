package br.gov.caixa.hackaton.repository.remote;

import br.gov.caixa.hackaton.entity.remote.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "SELECT * FROM produto p WHERE ?1 >= p.NU_MINIMO_MESES AND (?1 <= p.NU_MAXIMO_MESES OR p.nu_maximo_meses IS NULL) AND ?2 >= p.VR_MINIMO AND (?2 <= p.VR_MAXIMO OR p.vr_maximo IS NULL)", nativeQuery = true)
    public Produto BuscarProdutoParaSimulacao(Integer prazo, BigDecimal valor);
}
