package com.api.meuGuiaWeb.controller;

import com.api.meuGuiaWeb.roteiro.DadosCadastroRoteiro;
import com.api.meuGuiaWeb.roteiro.Roteiro;
import com.api.meuGuiaWeb.roteiro.RoteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("roteiro")
@CrossOrigin(origins = "http://localhost:3000")
public class RoteiroController {

    private final RoteiroService roteiroService;

    @Autowired
    public RoteiroController (RoteiroService roteiroService){
        this.roteiroService = roteiroService;
    }

    @GetMapping
    public ResponseEntity<List<Roteiro>> getAllRoteiros(){
        List<Roteiro> roteiros = roteiroService.getAllRoteiros();
        return new ResponseEntity<>(roteiros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roteiro> getRoteiroID(@PathVariable Long id) {
        Optional<Roteiro> produto = roteiroService.getProdutoById(id);
        return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> creatRoteiro(@RequestBody DadosCadastroRoteiro dadosCadastroRoteiro){
        Roteiro roteiro = roteiroService.createRoteiro(new Roteiro(dadosCadastroRoteiro));
        return new ResponseEntity<>( "Roteiro salvo com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoteiro(@PathVariable("id") Long id) {
        boolean deleted = roteiroService.deleteRoteiro(id);

        if (deleted) {
            return new ResponseEntity<>("Roteiro excluído com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Roteiro não encontrado ou não pôde ser excluído.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoteiro(@PathVariable("id") Long id, @RequestBody DadosCadastroRoteiro dadosCadastroRoteiro) {
        Roteiro roteiro = roteiroService.updateRoteiro(id, dadosCadastroRoteiro);

        if (roteiro != null) {
            return new ResponseEntity<>("Roteiro atualizado com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Roteiro não encontrado ou não pôde ser atualizado.", HttpStatus.NOT_FOUND);
        }
    }
}

