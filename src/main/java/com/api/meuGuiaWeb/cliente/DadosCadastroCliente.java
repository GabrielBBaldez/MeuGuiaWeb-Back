package com.api.meuGuiaWeb.cliente;

public record DadosCadastroCliente(
        Long id,
        String nome,
        String profissao,
        String telefone,
        String email,
        String cidade,
        String estado,
        String observacoes
) {}