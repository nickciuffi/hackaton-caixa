package br.gov.caixa.hackaton.entity.local;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "simulacoes")
@AllArgsConstructor
@Data
public class Simulacao {

    @Id
    @Column(name = "ID_SIMULACAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idSimulacao;

    @Column(name = "VR_DESEJADO", precision = 18, scale = 2)
    private BigDecimal valorDesejado;

    @Column(name = "NU_PRAZO")
    private Integer prazo;

    @Column(name = "VR_TOTAL_PARCELAS", precision = 18, scale = 2)
    private BigDecimal valorTotalParcelas;

    @Column(name = "NO_TIPO_SIMULACAO")
    private String tipo;

    public Simulacao(BigDecimal valorDesejado, Integer prazo, BigDecimal valorTotalParcelas, String tipo){
        this(null, valorDesejado, prazo, valorTotalParcelas, tipo);
    }
}
