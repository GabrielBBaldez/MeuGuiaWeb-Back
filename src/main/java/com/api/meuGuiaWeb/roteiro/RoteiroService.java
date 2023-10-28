package com.api.meuGuiaWeb.roteiro;

import com.api.meuGuiaWeb.programacao.DadosCadastroProgramacao;
import com.api.meuGuiaWeb.programacao.Programacao;
import com.api.meuGuiaWeb.programacao.ProgramacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoteiroService {

    private final RoteiroRepository roteiroRepository;
    private final ProgramacaoRepository programacaoRepository;

    @Autowired
    public RoteiroService(RoteiroRepository roteiroRepository, ProgramacaoRepository programacaoRepository) {
        this.roteiroRepository = roteiroRepository;
        this.programacaoRepository = programacaoRepository;
    }

    @Transactional
    public Roteiro createRoteiro(Roteiro roteiro) {
        roteiroRepository.save(roteiro);
        List<Programacao> programacaoList = roteiro.getProgramacaoList();
        programacaoList.forEach(programacao -> programacao.setRoteiro(roteiro));
        programacaoRepository.saveAll(programacaoList);
        return roteiro;
    }

    public List<Roteiro> getAllRoteiros() {
        return roteiroRepository.findAll();
    }

    public Optional<Roteiro> getProdutoById(Long id) {
        return roteiroRepository.findById(id);
    }

    public boolean deleteRoteiro(Long id) {
        if (roteiroRepository.existsById(id)) {
            roteiroRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Transactional
    public Roteiro updateRoteiro(Long id, DadosCadastroRoteiro dadosCadastroRoteiro) {
        try {
        Optional<Roteiro> existingRoteiro = roteiroRepository.findById(id);

        if (existingRoteiro.isPresent()) {
            Roteiro roteiro = getRoteiro(dadosCadastroRoteiro, existingRoteiro);

            List<Programacao> existingProgramacaoList = roteiro.getProgramacaoList();
            List<Programacao> newProgramacaoList = new ArrayList<>();

            Map<Integer, Programacao> existingProgramacaoMap = existingProgramacaoList.stream()
                    .collect(Collectors.toMap(Programacao::getSequencialDia, programacao -> programacao));

            for (DadosCadastroProgramacao novaProgramacao : dadosCadastroRoteiro.listaDeDias()) {
                int sequencialDia = novaProgramacao.sequencial_dia();
                if (existingProgramacaoMap.containsKey(sequencialDia)) {
                    updateProgramacaoAlreadyExisting(novaProgramacao, existingProgramacaoMap, sequencialDia);
                } else {
                    createNewProgramacao(novaProgramacao, roteiro, newProgramacaoList);
                }
            }
                if(!newProgramacaoList.isEmpty()){
                    programacaoRepository.saveAll(newProgramacaoList);
                }
            return roteiroRepository.save(roteiro);
        } else {
            return null;
        }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Roteiro updateLotacao(Long id, boolean lotado) {
        try {
            Optional<Roteiro> existingRoteiro = roteiroRepository.findById(id);
            if (existingRoteiro.isPresent()) {
                Roteiro roteiro = existingRoteiro.get();
                roteiro.setLotado(lotado);
                return roteiroRepository.save(roteiro);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static void updateProgramacaoAlreadyExisting(DadosCadastroProgramacao novaProgramacao, Map<Integer, Programacao> existingProgramacaoMap, int sequencialDia) {
        Programacao existingProgramacao = existingProgramacaoMap.get(sequencialDia);
        existingProgramacao.setAtividade(novaProgramacao.atividade());
        existingProgramacao.setLocalDia(novaProgramacao.local_dia());
    }

    private static void createNewProgramacao(DadosCadastroProgramacao novaProgramacao, Roteiro roteiro, List<Programacao> newProgramacaoList) {
        Programacao newProgramacao = new Programacao(novaProgramacao);
        newProgramacao.setRoteiro(roteiro);
        newProgramacao.setAtividade(novaProgramacao.atividade());
        newProgramacao.setLocalDia(novaProgramacao.local_dia());
        newProgramacaoList.add(newProgramacao);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static Roteiro getRoteiro(DadosCadastroRoteiro dadosCadastroRoteiro, Optional<Roteiro> existingRoteiro) {
        Roteiro roteiro = existingRoteiro.get();
        Roteiro updateRoteiro = new Roteiro(dadosCadastroRoteiro);

        roteiro.setDataPartida(updateRoteiro.getDataPartida());
        roteiro.setDataChegada(updateRoteiro.getDataChegada());
        roteiro.setNomeRoteiro(updateRoteiro.getNomeRoteiro());
        roteiro.setAtracoes(updateRoteiro.getAtracoes());
        roteiro.setDescricao(updateRoteiro.getDescricao());
        roteiro.setUrlImagem(updateRoteiro.getUrlImagem());
        return roteiro;
    }
}
