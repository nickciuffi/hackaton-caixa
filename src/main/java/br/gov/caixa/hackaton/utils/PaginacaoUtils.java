package br.gov.caixa.hackaton.utils;

import br.gov.caixa.hackaton.dto.PaginacaoDTO;

import java.util.Collections;
import java.util.List;

public class PaginacaoUtils {

    public static <T> PaginacaoDTO<T> gerarPaginacao(List<T> dados, int pagina, int qtdRegistrosPagina){

        int totalRegistros = dados.size();
        int inicio = pagina * qtdRegistrosPagina;
        int fim = Math.min(inicio + qtdRegistrosPagina, totalRegistros);

        List<T> registrosPaginados = inicio < totalRegistros ? dados.subList(inicio, fim) : Collections.emptyList();

        PaginacaoDTO<T> dtoPaginado = new PaginacaoDTO<>();
        dtoPaginado.setPagina(pagina);
        dtoPaginado.setQtdRegistros(totalRegistros);
        dtoPaginado.setQtdRegistrosPagina(qtdRegistrosPagina);
        dtoPaginado.setRegistros(registrosPaginados);

        return dtoPaginado;
    }
}
