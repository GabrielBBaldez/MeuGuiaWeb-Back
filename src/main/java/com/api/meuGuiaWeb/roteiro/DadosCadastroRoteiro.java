package com.api.meuGuiaWeb.roteiro;

import com.api.meuGuiaWeb.programacao.DadosCadastroProgramacao;
import com.api.meuGuiaWeb.programacao.Programacao;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroRoteiro (
        LocalDate dataPartida,
        LocalDate dataChegada,
        String nomeRoteiro,
        String atracoes,
        String descricao,
        String urlImagem,
        List<DadosCadastroProgramacao> listaDeDias
) {
}
