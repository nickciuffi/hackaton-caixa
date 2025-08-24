package br.gov.caixa.hackaton.utils;

import br.gov.caixa.hackaton.dto.PaginacaoDTO;

import java.util.ArrayList;
import java.util.List;

public class PaginacaoUtils {

    private PaginacaoUtils(){}

    public static <T> PaginacaoDTO<T> gerarPaginacao(List<T> dados, int pagina, int qtdRegistrosPagina){

        int totalRegistros = dados.size();
        int inicio = (pagina - 1) * qtdRegistrosPagina;
        int qtdRegistrosDepoisDoInicio = totalRegistros - inicio;

        int fim = Math.min(inicio + qtdRegistrosPagina, inicio + qtdRegistrosDepoisDoInicio);

        List<T> registrosPaginados = qtdRegistrosDepoisDoInicio > 0 ? dados.subList(inicio, fim) : new ArrayList<>();

        PaginacaoDTO<T> dtoPaginado = new PaginacaoDTO<>();
        dtoPaginado.setPagina(pagina);
        dtoPaginado.setQtdRegistros(totalRegistros);
        dtoPaginado.setQtdRegistrosPagina(qtdRegistrosPagina);
        dtoPaginado.setRegistros(registrosPaginados);

        return dtoPaginado;
    }
}
