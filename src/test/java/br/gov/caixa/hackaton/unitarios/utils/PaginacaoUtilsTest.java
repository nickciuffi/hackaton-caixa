package br.gov.caixa.hackaton.unitarios.utils;

import br.gov.caixa.hackaton.dto.PaginacaoDTO;
import br.gov.caixa.hackaton.utils.PaginacaoUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginacaoUtilsTest {

    @Test
    void gerarPaginacaoTest1(){
        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        PaginacaoDTO<Integer> intsPaginadas = PaginacaoUtils.gerarPaginacao(ints, 3, 2);
        assertEquals(10, intsPaginadas.getQtdRegistros());
        assertEquals(2, intsPaginadas.getRegistros().size());
        assertEquals(6, intsPaginadas.getRegistros().get(1));
        assertEquals(3, intsPaginadas.getPagina());
        assertEquals(2, intsPaginadas.getQtdRegistrosPagina());
    }

    @Test
    void gerarPaginacaoTest2(){
        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        PaginacaoDTO<Integer> intsPaginadas = PaginacaoUtils.gerarPaginacao(ints, 1, 4);
        assertEquals(12, intsPaginadas.getQtdRegistros());
        assertEquals(4, intsPaginadas.getRegistros().size());
        assertEquals(3, intsPaginadas.getRegistros().get(2));
        assertEquals(1, intsPaginadas.getPagina());
        assertEquals(4, intsPaginadas.getQtdRegistrosPagina());
    }
    @Test
    void gerarPaginacaoTest3(){
        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        PaginacaoDTO<Integer> intsPaginadas = PaginacaoUtils.gerarPaginacao(ints, 1, 6);
        assertEquals(4, intsPaginadas.getQtdRegistros());
        assertEquals(4, intsPaginadas.getRegistros().size());
        assertEquals(3, intsPaginadas.getRegistros().get(2));
        assertEquals(1, intsPaginadas.getPagina());
        assertEquals(6, intsPaginadas.getQtdRegistrosPagina());
    }

    @Test
    void gerarPaginacaoTest4(){
        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        PaginacaoDTO<Integer> intsPaginadas = PaginacaoUtils.gerarPaginacao(ints, 2, 6);
        assertEquals(4, intsPaginadas.getQtdRegistros());
        assertEquals(0, intsPaginadas.getRegistros().size());
        assertEquals(2, intsPaginadas.getPagina());
        assertEquals(6, intsPaginadas.getQtdRegistrosPagina());
    }
}
