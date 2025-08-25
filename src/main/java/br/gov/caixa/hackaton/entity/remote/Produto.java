package br.gov.caixa.hackaton.entity.remote;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Produto {

    @Id
    @Column(name = "CO_PRODUTO")
    private Integer coProduto;

    @Column(name = "NO_PRODUTO")
    private String noProduto;

    @Column(name = "PC_TAXA_JUROS", precision = 10, scale = 9)
    private BigDecimal pcTaxaJuros;

    @Column(name = "NU_MINIMO_MESES")
    private Short nuMinimoMeses;

    @Column(name = "NU_MAXIMO_MESES")
    private Short nuMaximoMeses;

    @Column(name = "VR_MINIMO", precision = 18, scale = 2)
    private BigDecimal vrMinimo;

    @Column(name = "VR_MAXIMO", precision = 18, scale = 2)
    private BigDecimal vrMaximo;
}
