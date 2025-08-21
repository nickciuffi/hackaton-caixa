package br.gov.caixa.hackaton.unitarios.service;

import br.gov.caixa.hackaton.dto.telemetria.ConsultarTelemetriaResponseDTO;
import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;
import br.gov.caixa.hackaton.repository.local.TelemetriaRepository;
import br.gov.caixa.hackaton.service.implementation.TelemetriaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TelemetriaServiceTest {

    @Mock
    private TelemetriaRepository telemetriaRepository;

    @InjectMocks
    private TelemetriaServiceImpl service;

    private static final String NOME_API = "/simulacao";
    private static final Long TEMPO_EXECUCAO = 123123L;
    private static final BigDecimal TEMPO_MEDIO = new BigDecimal("123123.22");
    private static final String METODO_HTTP = "GET";
    private static final Long QTD_REQUISICOES = 12L;
    private static final Long TEMPO = 12123313L;
    private static final String DATA = "20-09-2025";
    private static final BigDecimal PERCENTUAL_SUCESSO = new BigDecimal("0.75");

    @Test
    void cadastrarTelemetriaTest(){
        TelemetriaDTO req = TelemetriaDTO
                .builder()
                .nomeApi(NOME_API)
                .methodoHttp(METODO_HTTP)
                .isSucesso(true)
                .tempoExecucao(TEMPO_EXECUCAO)
                .build();

        when(telemetriaRepository.save(any())).thenReturn(null);
        assertDoesNotThrow(() -> service.cadastrarTelemetria(req));
    }

    @Test
    void consultarTelemetriaPorDataTest(){
        Object[] obj1 = new Object[]{
                NOME_API,
                METODO_HTTP,
                QTD_REQUISICOES,
                TEMPO_MEDIO,
                TEMPO,
                TEMPO,
                PERCENTUAL_SUCESSO
        };
        List<Object[]> resEnt = new ArrayList<>(Arrays.asList(obj1, obj1, obj1, obj1));

        when(telemetriaRepository.consultarTelemetriaPorData(any())).thenReturn(resEnt);

        ConsultarTelemetriaResponseDTO res = service.consultarTelemetriaPorData(DATA);

        assertNotNull(res);
        assertEquals(4, res.getListaEndpoints().size());
        assertEquals(DATA, res.getDataReferencia());
        assertEquals(QTD_REQUISICOES, res.getListaEndpoints().get(1).getQtdRequisicoes());
        assertEquals(NOME_API, res.getListaEndpoints().get(3).getNomeApi());
        assertEquals(TEMPO, res.getListaEndpoints().get(2).getTempoMaximo());
        assertEquals(PERCENTUAL_SUCESSO, res.getListaEndpoints().get(0).getPercentualSucesso());

        verify(telemetriaRepository).consultarTelemetriaPorData(any());
    }
}
