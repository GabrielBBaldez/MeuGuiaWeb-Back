package com.api.meuGuiaWeb.roteiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoteiroService {

    private final RoteiroRepository roteiroRepository;

    @Autowired
    public RoteiroService(RoteiroRepository roteiroRepository){
        this.roteiroRepository = roteiroRepository;
    }

    public Roteiro createRoteiro(Roteiro roteiro){
        return roteiroRepository.save(roteiro);
    }

    public List<Roteiro> getAllRoteiros(){
        return roteiroRepository.findAll();
    }
}
