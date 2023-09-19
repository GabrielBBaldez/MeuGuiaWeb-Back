package com.api.meuGuiaWeb.programacao;

import com.api.meuGuiaWeb.roteiro.Roteiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramacaoService {

    private final ProgramacaoRepository programacaoRepository;

    @Autowired
    public ProgramacaoService(ProgramacaoRepository programacaoRepository) {
        this.programacaoRepository = programacaoRepository;
    }

}
