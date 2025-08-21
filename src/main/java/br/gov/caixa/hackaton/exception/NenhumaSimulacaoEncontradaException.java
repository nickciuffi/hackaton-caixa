package br.gov.caixa.hackaton.exception;

public class NenhumaSimulacaoEncontradaException extends RuntimeException {
    public NenhumaSimulacaoEncontradaException() {
        super("Nenhuma simulação encontrada!");
    }
}
