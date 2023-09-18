package com.api.meuGuiaWeb.controler;

import com.api.meuGuiaWeb.roteiro.DadosCadastroRoteiro;
import com.api.meuGuiaWeb.roteiro.Roteiro;
import com.api.meuGuiaWeb.roteiro.RoteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public String creatRoteiro(@RequestBody DadosCadastroRoteiro dadosCadastroRoteiro){
        Roteiro roteiro = roteiroService.createRoteiro(new Roteiro(dadosCadastroRoteiro));
        return "Roteiro salvo com sucesso " + roteiro.toString();
    }


}
