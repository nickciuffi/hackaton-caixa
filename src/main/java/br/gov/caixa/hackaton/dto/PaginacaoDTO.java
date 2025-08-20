package br.gov.caixa.hackaton.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginacaoDTO<T> {
    private Integer pagina;
    private Integer qtdRegistros;
    private Integer qtdRegistrosPagina;
    private List<T> registros;
}
