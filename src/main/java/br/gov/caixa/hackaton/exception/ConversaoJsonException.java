package br.gov.caixa.hackaton.exception;

public class ConversaoJsonException extends RuntimeException {
    public ConversaoJsonException() {
        super("Erro ao fazer a conversão para JSON");
    }
}
