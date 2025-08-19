package br.gov.caixa.hackaton.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@Data
public class ApiResponse<T> {

    private T response;

    private List<String> message = new ArrayList<>();

    public ApiResponse(T response, String message) {
        this.response = response;
        this.message.add(message);
    }

    public ApiResponse(String message) {
        this.message.add(message);
    }

    public ApiResponse(List<String> message) {
        this.message = message;
    }
}
