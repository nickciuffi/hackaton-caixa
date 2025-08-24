package br.gov.caixa.hackaton.exception;

public class TelemetriaNaoEncontradaException extends RuntimeException {
    public TelemetriaNaoEncontradaException(String data) {
        super("Nenhuma informação de telemetria encontrada na data: " + data);
    }
}
