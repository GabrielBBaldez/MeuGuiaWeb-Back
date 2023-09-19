package com.api.meuGuiaWeb.programacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramacaoRepository extends JpaRepository<Programacao,Long> {
}
