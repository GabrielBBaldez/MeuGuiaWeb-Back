package com.api.meuGuiaWeb.programacao;

import com.api.meuGuiaWeb.roteiro.Roteiro;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programacao", schema = "public")
@Getter
@Setter
public class Programacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_roteiro", referencedColumnName = "id")
    private Roteiro roteiro;

    @Column(name = "sequencial_dia")
    private Integer sequencialDia;

    @Column(name = "atividade", columnDefinition = "text")
    private String atividade;

    @Column(name = "local_dia")
    private String localDia;

    public Programacao (DadosCadastroProgramacao dadosCadastroProgramacao){
        this.atividade = dadosCadastroProgramacao.atividade();
        this.localDia =dadosCadastroProgramacao.local_dia();
        this.sequencialDia = dadosCadastroProgramacao.sequencial_dia();
    }

}
