package br.gov.caixa.hackaton.unitarios.service;

import br.gov.caixa.hackaton.config.eventhub.EventHubConfiguration;
import br.gov.caixa.hackaton.dto.simulacao.ConsultarSimulacoesRequestDTO;
import br.gov.caixa.hackaton.exception.ConversaoJsonException;
import br.gov.caixa.hackaton.service.implementation.EventHubServiceImpl;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.azure.messaging.eventhubs.models.CreateBatchOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventHubServiceTest {

    @Mock
    private EventHubProducerAsyncClient producerMock;

    @Mock
    private EventDataBatch batchMock;

    @Mock
    private EventHubConfiguration configMock;

    private EventHubServiceImpl eventHubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // inicializa os mocks

        // Simula retorno da connection string
        when(configMock.getConnectionString()).thenReturn( "Endpoint=sb://testeeee/;" +
                "SharedAccessKeyName=test;" +
                "SharedAccessKey=testeeeeeee;" +
                "EntityPath=teste");

        // Instancia manualmente com o mock
        eventHubService = new EventHubServiceImpl(configMock);

        // Injeta o mock do producer
        ReflectionTestUtils.setField(eventHubService, "producer", producerMock);
    }

    @Test
    void enviarTest() {
        ConsultarSimulacoesRequestDTO dados = new ConsultarSimulacoesRequestDTO();

        Mono<EventDataBatch> batchMono = Mono.just(batchMock);
        when(producerMock.createBatch(any(CreateBatchOptions.class))).thenReturn(batchMono);
        when(batchMock.tryAdd(any(EventData.class))).thenReturn(true);
        when(producerMock.send(batchMock)).thenReturn(Mono.empty());

        eventHubService.enviar(dados);

        verify(producerMock).send(batchMock);
    }

    @Test
    void enviarComErroNaConversaoJsonTest() {
        GetterQueCausaErroNaConversaoJson dados = new GetterQueCausaErroNaConversaoJson();

        assertThrows(ConversaoJsonException.class, () -> eventHubService.enviar(dados));

    }

    //não é possível mockar o ObjectMapper do jackson, por isso resolvi causar o erro naturalmente, com uma classe que não pode ser convertida para json
    static class GetterQueCausaErroNaConversaoJson {
        public String getValor() {
            throw new RuntimeException("Erro no getter");
        }
    }

}
