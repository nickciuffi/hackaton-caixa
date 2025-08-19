package br.gov.caixa.hackaton.entity.local;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "simulacoes")
public class Simulacao {

    @Id
    @Column(name = "ID_SIMULACAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idSimulacao;

    @Column(name = "VR_DESEJADO", precision = 18, scale = 2)
    private BigDecimal valorDesejado;

    @Column(name = "NU_PRAZO")
    private short prazo;

    @Column(name = "VR_TOTAL_PARCELAS", precision = 18, scale = 2)
    private BigDecimal valorTotalParcelas;
}
