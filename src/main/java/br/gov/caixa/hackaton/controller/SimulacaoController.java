package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.PaginacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.ConsultarSimulacoesRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.SimulacaoResponseDTO;
import br.gov.caixa.hackaton.service.SimulacaoService;
import br.gov.caixa.hackaton.utils.PaginacaoUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

    @Autowired
    private SimulacaoService simulacaoService;

    @PostMapping("/realizar")
    public ResponseEntity<ApiResponse<SimulacaoResponseDTO>> realizarSimulacao(@RequestBody @Valid SimulacaoRequestDTO req){
        SimulacaoResponseDTO res = simulacaoService.realizarSimulacao(req);
        return ResponseEntity.ok().body(new ApiResponse<SimulacaoResponseDTO>(res, "Simulação realizada com sucesso!"));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<PaginacaoDTO<SimulacaoDTO>>> consultarSimulacoesComPaginacao(@Valid @ModelAttribute ConsultarSimulacoesRequestDTO req){
        List<SimulacaoDTO> res = simulacaoService.consultarSimulacoes();
        return ResponseEntity.ok()
                .body(new ApiResponse<>(
                        PaginacaoUtils.gerarPaginacao(res, req.getPagina(), req.getQtdRegistrosPagina()),
                        "Simulações consultadas com sucesso!"
                ));
    }
}
