package br.gov.caixa.hackaton.service.implementation;

import br.gov.caixa.hackaton.config.eventhub.EventHubConfiguration;
import br.gov.caixa.hackaton.exception.ConversaoJsonException;
import br.gov.caixa.hackaton.exception.EventSenderNaoInicializado;
import br.gov.caixa.hackaton.service.EventSenderService;
import com.azure.messaging.eventhubs.*;
import com.azure.messaging.eventhubs.models.CreateBatchOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EventHubServiceImpl implements EventSenderService {

    private final EventHubProducerAsyncClient producer;
    private final CreateBatchOptions options;

    private final ObjectMapper objectMapper;

    public EventHubServiceImpl(@Qualifier("eventHubConfiguration") EventHubConfiguration properties) {

        String connectionString = properties.getConnectionString();
        this.producer = new EventHubClientBuilder()
                .connectionString(connectionString)
                .buildAsyncProducerClient();

        this.options = new CreateBatchOptions();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> void enviar(T dados) throws EventSenderNaoInicializado, ConversaoJsonException{
        if(producer == null) throw new EventSenderNaoInicializado();

        try {
            String dadosJson = objectMapper.writeValueAsString(dados);
            producer.createBatch(options).subscribe(batch -> {

                batch.tryAdd(new EventData(dadosJson));

                producer.send(batch).subscribe();
            });
        }
        catch(JsonProcessingException e){
            throw new ConversaoJsonException();
        }

    }
}
