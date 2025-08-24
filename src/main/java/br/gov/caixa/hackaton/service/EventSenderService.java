package br.gov.caixa.hackaton.service;

public interface EventSenderService {

    public <T> void enviar(T dados);
}
