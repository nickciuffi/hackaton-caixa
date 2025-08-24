package br.gov.caixa.hackaton.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/collection")
@RestController
@AllArgsConstructor
public class CollectionController {

    @GetMapping("")
    @Hidden
    public ResponseEntity<Resource> baixarCollection() {
        Resource resource = new ClassPathResource("static/collection/postman_collection.json");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=postman_collection.json")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
