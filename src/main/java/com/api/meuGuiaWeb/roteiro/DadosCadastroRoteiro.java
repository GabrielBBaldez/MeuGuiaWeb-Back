package com.api.meuGuiaWeb.roteiro;

import java.time.LocalDate;

public record DadosCadastroRoteiro (
        LocalDate dataPartida,
        LocalDate dataChegada,
        String nomeRoteiro,
        String atracoes,
        String descricao,
        String urlImagem,
        LocalDate diaUm,
        String localUm,
        String descricaoDiaUm
) {
}
