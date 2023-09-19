package com.api.meuGuiaWeb.roteiro;

import com.api.meuGuiaWeb.programacao.Programacao;
import com.api.meuGuiaWeb.programacao.ProgramacaoRepository;
import com.api.meuGuiaWeb.programacao.ProgramacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoteiroService {

    private final RoteiroRepository roteiroRepository;
    private final ProgramacaoRepository programacaoRepository;

    @Autowired
    public RoteiroService(RoteiroRepository roteiroRepository, ProgramacaoRepository programacaoRepository){
        this.roteiroRepository = roteiroRepository;
        this.programacaoRepository = programacaoRepository;
    }
    @Transactional
    public Roteiro createRoteiro(Roteiro roteiro){
        roteiroRepository.save(roteiro);
        List<Programacao> programacaoList = roteiro.getProgramacaoList();
        programacaoList.forEach(programacao -> programacao.setRoteiro(roteiro));
        programacaoRepository.saveAll(programacaoList);
        return roteiro;
    }

    public List<Roteiro> getAllRoteiros(){
        return roteiroRepository.findAll();
    }
}
