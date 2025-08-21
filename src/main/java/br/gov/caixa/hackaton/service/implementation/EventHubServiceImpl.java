package br.gov.caixa.hackaton.service.implementation;

import br.gov.caixa.hackaton.config.eventhub.EventHubConfiguration;
import br.gov.caixa.hackaton.exception.ConversaoJsonException;
import br.gov.caixa.hackaton.exception.EnviarEventSenderException;
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

    private EventHubProducerAsyncClient producer;
    private CreateBatchOptions options;

    private ObjectMapper objectMapper;

    private EventHubConfiguration properties;

    public EventHubServiceImpl(@Qualifier("eventHubConfiguration") EventHubConfiguration properties) {
        this.properties = properties;

        this.producer = new EventHubClientBuilder()
                .connectionString(properties.getConnectionString())
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

                producer.send(batch).subscribe(unused -> {
                    System.out.println("Sucesso" + unused);
                }, error -> {
                    throw new EnviarEventSenderException();
                });
            });
        }
        catch(JsonProcessingException e){
            throw new ConversaoJsonException();
        }

    }
}
