package br.gov.caixa.hackaton.exception;

public class EnviarEventSenderException extends RuntimeException {
    public EnviarEventSenderException() {
        super("Erro ao enviar informações no EventSender");
    }
}
