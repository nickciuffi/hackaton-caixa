package br.gov.caixa.hackaton.controller;

import br.gov.caixa.hackaton.entity.Produto;
import br.gov.caixa.hackaton.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("")
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();

        return ResponseEntity.ok().body(produtos);
    }
}
