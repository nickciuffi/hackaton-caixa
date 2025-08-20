package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.entity.remote.Produto;
import br.gov.caixa.hackaton.repository.remote.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    @GetMapping("")
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();

        return ResponseEntity.ok().body(produtos);
    }
}
