package com.api.meuGuiaWeb.controller;

import com.api.meuGuiaWeb.roteiro.DadosCadastroRoteiro;
import com.api.meuGuiaWeb.roteiro.Roteiro;
import com.api.meuGuiaWeb.roteiro.RoteiroService;
import com.api.meuGuiaWeb.usuario.DadosLoginUsario;
import com.api.meuGuiaWeb.usuario.Usuario;
import com.api.meuGuiaWeb.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> createToken(@RequestBody DadosLoginUsario dadosLoginUsario) {
        try {
            Usuario usuario = usuarioService.atualizarToken(dadosLoginUsario);
            if (usuario != null) {
                return new ResponseEntity<>("Token atualizado com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar o token: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

