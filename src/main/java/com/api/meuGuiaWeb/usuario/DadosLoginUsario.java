package com.api.meuGuiaWeb.usuario;

public record DadosLoginUsario(
        String email,
        String senha,
        String token
) {
}
