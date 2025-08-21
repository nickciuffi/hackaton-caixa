package br.gov.caixa.hackaton.entity.local;

import br.gov.caixa.hackaton.dto.simulacao.ResultadoSimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.utils.ParcelaUtils;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "simulacoes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "DT_SIMULACAO")
    private LocalDate dataSimulacao;

    @Column(name = "CO_PRODUTO")
    private Integer coProduto;

    @Column(name = "NO_PRODUTO")
    private String noProduto;

    @Column(name = "VR_MEDIO_PRESTACAO", precision = 18, scale = 2)
    private BigDecimal valorMedioPrestacao;

    @Column(name = "PC_TAXA_JUROS", precision = 10, scale = 9)
    private BigDecimal pcTaxaJuros;

    public Simulacao(ResultadoSimulacaoDTO simulacao, SimulacaoRequestDTO req, Produto prod){
        this(null, req.getValorDesejado(), req.getPrazo(), ParcelaUtils.calcularTotalParcelas(simulacao.getParcelas()),
                simulacao.getTipo(), LocalDate.now(), prod.getCoProduto(), prod.getNoProduto(),
                ParcelaUtils.calcularMediaPrestacao(simulacao.getParcelas()), prod.getPcTaxaJuros());
    }
}
