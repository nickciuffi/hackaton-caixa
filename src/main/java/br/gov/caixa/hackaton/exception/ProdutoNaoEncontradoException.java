package br.gov.caixa.hackaton.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado para os critérios informados.");
    }
}
