package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.PaginacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.*;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdResponseDTO;
import br.gov.caixa.hackaton.service.SimulacaoService;
import br.gov.caixa.hackaton.utils.PaginacaoUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulacao")
@AllArgsConstructor
public class SimulacaoController {

    private SimulacaoService simulacaoService;

    @PostMapping("/realizar")
    public ResponseEntity<ApiResponse<SimulacaoResponseDTO>> realizarSimulacao(@RequestBody @Valid SimulacaoRequestDTO req){
        SimulacaoResponseDTO res = simulacaoService.realizarSimulacao(req);
        return ResponseEntity.ok().body(new ApiResponse<SimulacaoResponseDTO>(res, "Simulação realizada com sucesso!"));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<PaginacaoDTO<SimulacaoDTO>>> consultarSimulacoesComPaginacao(@Valid @ModelAttribute ConsultarSimulacoesRequestDTO req){
        List<SimulacaoDTO> res = simulacaoService.consultarSimulacoes(req);
        PaginacaoDTO<SimulacaoDTO> resPaginado = PaginacaoUtils.gerarPaginacao(res, req.getPagina(), req.getQtdRegistrosPagina());
        return ResponseEntity.ok()
                .body(new ApiResponse<>(
                        resPaginado,
                        !resPaginado.getRegistros().isEmpty() ? "Simulações consultadas com sucesso!" : "Página vazia!"
                ));
    }

    @GetMapping("/prod-data")
    public ResponseEntity<ApiResponse<SimulacaoPorDataEProdResponseDTO>> consultarSimulacoesPorDataEProd(@Valid @ModelAttribute SimulacaoPorDataEProdRequestDTO req){
        SimulacaoPorDataEProdResponseDTO res = SimulacaoPorDataEProdResponseDTO.builder().dataReferencia(req.getDataReferencia()).simulacoes(simulacaoService.consultarSimulacoesPorDataEProd(req)).build();
        return ResponseEntity.ok().body(new ApiResponse<SimulacaoPorDataEProdResponseDTO>(res, "Simulações consultadas por produto e data com sucesso!"));
    }
}
