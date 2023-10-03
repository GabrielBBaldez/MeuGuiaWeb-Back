package com.api.meuGuiaWeb.roteiro;

import com.api.meuGuiaWeb.programacao.Programacao;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roteiro", schema = "public")
@Getter
@Setter
public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_partida")
    private LocalDate dataPartida;

    @Column(name = "data_chegada")
    private LocalDate dataChegada;

    @Column(name = "nome_roteiro")
    private String nomeRoteiro;

    @Column(name = "atracoes", columnDefinition = "text")
    private String atracoes;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @Column(name = "url_imagem", columnDefinition = "text")
    private String urlImagem;

    @OneToMany(mappedBy = "roteiro")
    @JsonManagedReference
    private List<Programacao> programacaoList;

    //Usar anotação Spring Beanconverter
    public Roteiro ( DadosCadastroRoteiro dadosCadastroRoteiro){

        this.dataPartida = dadosCadastroRoteiro.dataPartida();
        this.dataChegada = dadosCadastroRoteiro.dataChegada();
        this.nomeRoteiro = dadosCadastroRoteiro.nomeRoteiro();
        this.atracoes = dadosCadastroRoteiro.atracoes();
        this.descricao = dadosCadastroRoteiro.descricao();
        this.urlImagem = dadosCadastroRoteiro.urlImagem();
        this.programacaoList = dadosCadastroRoteiro.listaDeDias()
                .stream()
                .map(Programacao::new)
                .collect(Collectors.toList());

    }
}
