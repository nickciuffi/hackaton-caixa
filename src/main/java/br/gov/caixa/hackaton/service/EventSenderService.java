package br.gov.caixa.hackaton.service;

import br.gov.caixa.hackaton.exception.EventSenderNaoInicializado;
import com.azure.messaging.eventhubs.EventHubProducerClient;

public interface EventSenderService {

    public <T> void enviar(T dados);
}
