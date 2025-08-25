package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.dto.ApiResponse;
import br.gov.caixa.hackaton.dto.PaginacaoDTO;
import br.gov.caixa.hackaton.dto.simulacao.*;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdRequestDTO;
import br.gov.caixa.hackaton.dto.simulacao.data_prod.SimulacaoPorDataEProdResponseDTO;
import br.gov.caixa.hackaton.dto.swagger.RetornoDeErroNosParametrosSwaggerDTO;
import br.gov.caixa.hackaton.dto.swagger.RetornoDeErroProdutoNaoEncontradoSwaggerDTO;
import br.gov.caixa.hackaton.dto.swagger.RetornoDeErroSimulacaoNaoEncontradaSwaggerDTO;
import br.gov.caixa.hackaton.service.SimulacaoService;
import br.gov.caixa.hackaton.utils.PaginacaoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
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
    @Operation(summary = "Realizar uma simulação", description = "Realizar uma simulação, com um produto que se encaixe nos parâmetros informados, nos sistemas de amortização SAC e PRICE")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro nos parâmetros.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroNosParametrosSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Produto não encontrado para os critérios informados.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroProdutoNaoEncontradoSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Simulação realizada com sucesso!")
      })
    public ResponseEntity<ApiResponse<SimulacaoResponseDTO>> realizarSimulacao(@RequestBody @Valid SimulacaoRequestDTO req){
        SimulacaoResponseDTO res = simulacaoService.realizarSimulacao(req);
        return ResponseEntity.ok().body(new ApiResponse<SimulacaoResponseDTO>(res, "Simulação realizada com sucesso!"));
    }

    @GetMapping("")
    @Operation(summary = "Consultar simulações", description = "Consultar todas as simulações feitas, com paginação")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro nos parâmetros.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroNosParametrosSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma simulação encontrada!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroSimulacaoNaoEncontradaSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Simulações consultadas com sucesso!")
    })
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
    @Operation(summary = "Consultar simulações", description = "Consultar as simulações feitas, por data e produto")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro nos parâmetros.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroNosParametrosSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma simulação encontrada!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErroSimulacaoNaoEncontradaSwaggerDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Simulações consultadas com sucesso!")
    })
    public ResponseEntity<ApiResponse<SimulacaoPorDataEProdResponseDTO>> consultarSimulacoesPorDataEProd(@Valid @ModelAttribute SimulacaoPorDataEProdRequestDTO req){
        SimulacaoPorDataEProdResponseDTO res = SimulacaoPorDataEProdResponseDTO.builder().dataReferencia(req.getDataReferencia()).simulacoes(simulacaoService.consultarSimulacoesPorDataEProd(req)).build();
        return ResponseEntity.ok().body(new ApiResponse<SimulacaoPorDataEProdResponseDTO>(res, "Simulações consultadas por produto e data com sucesso!"));
    }
}
