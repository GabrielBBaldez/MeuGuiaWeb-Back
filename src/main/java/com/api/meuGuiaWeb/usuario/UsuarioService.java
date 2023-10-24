package com.api.meuGuiaWeb.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario atualizarToken(DadosLoginUsario dadosLoginUsario){
       Usuario usuario = usuarioRepository.findByEmailAndSenha(dadosLoginUsario.email(), dadosLoginUsario.senha());
        if (usuario != null) {
            usuario.setToken(dadosLoginUsario.token());
            usuarioRepository.save(usuario);
            return usuario;
        } else {
            return null;
        }
    }

}
