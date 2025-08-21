package br.gov.caixa.hackaton.exception;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ApiResponse<Object>> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));
    }

    @ExceptionHandler(NenhumaSimulacaoEncontradaException.class)
    public ResponseEntity<ApiResponse<Object>> handleProdutoNaoEncontradoException(NenhumaSimulacaoEncontradaException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> mensagens = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toCollection(ArrayList::new));
        mensagens.add(0, "Existem erros nos parâmetros enviados!");
        return ResponseEntity.badRequest().body(new ApiResponse<>(mensagens));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Erro interno do servidor."));
    }
}
