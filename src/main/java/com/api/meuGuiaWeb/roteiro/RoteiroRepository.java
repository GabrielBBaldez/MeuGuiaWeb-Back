package com.api.meuGuiaWeb.roteiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoteiroRepository extends JpaRepository<Roteiro,Long> {
}
