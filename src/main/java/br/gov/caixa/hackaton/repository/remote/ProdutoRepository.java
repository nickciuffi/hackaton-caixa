package br.gov.caixa.hackaton.repository.remote;

import br.gov.caixa.hackaton.entity.remote.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "SELECT * FROM produto p WHERE ?1 BETWEEN p.NU_MINIMO_MESES AND p.NU_MAXIMO_MESES AND ?2 BETWEEN p.VR_MINIMO AND p.VR_MAXIMO", nativeQuery = true)
    public Produto BuscarProdutoParaSimulacao(Integer prazo, BigDecimal valor);
}
