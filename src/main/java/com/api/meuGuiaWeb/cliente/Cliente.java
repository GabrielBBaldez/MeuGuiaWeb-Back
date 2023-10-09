package com.api.meuGuiaWeb.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente", schema = "public")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "profissao", length = 255)
    private String profissao;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "cidade", length = 255)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    public  Cliente (DadosCadastroCliente dadosCadastroCliente) {
        this.setId(dadosCadastroCliente.id());
        this.setNome(dadosCadastroCliente.nome());
        this.setProfissao(dadosCadastroCliente.profissao());
        this.setTelefone(dadosCadastroCliente.telefone());
        this.setEmail(dadosCadastroCliente.email());
        this.setCidade(dadosCadastroCliente.cidade());
        this.setEstado(dadosCadastroCliente.estado());
        this.setObservacoes(dadosCadastroCliente.observacoes());
    }
}
