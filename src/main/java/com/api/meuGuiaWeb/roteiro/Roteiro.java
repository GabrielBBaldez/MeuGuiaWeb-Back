package com.api.meuGuiaWeb.roteiro;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roteiro", schema = "public")
@Data
public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPartida;
    private LocalDate dataChegada;
    private String nomeRoteiro;
    private String atracoes;
    private String descricao;
    private String urlImagem;
    private LocalDate diaUm;
    private String localUm;
    private String descricaoDiaUm;

    //Usar anotação Spring Beanconverter
    public Roteiro ( DadosCadastroRoteiro dadosCadastroRoteiro){

        this.dataPartida = dadosCadastroRoteiro.dataPartida();
        this.dataChegada = dadosCadastroRoteiro.dataChegada();
        this.nomeRoteiro = dadosCadastroRoteiro.nomeRoteiro();
        this.atracoes = dadosCadastroRoteiro.atracoes();
        this.descricao = dadosCadastroRoteiro.descricao();
        this.urlImagem = dadosCadastroRoteiro.urlImagem();
        this.diaUm = dadosCadastroRoteiro.diaUm();
        this.localUm = dadosCadastroRoteiro.localUm();
        this.descricaoDiaUm = dadosCadastroRoteiro.descricaoDiaUm();
    }
}
