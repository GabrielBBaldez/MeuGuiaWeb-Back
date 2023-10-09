package com.api.meuGuiaWeb.cliente;

import com.api.meuGuiaWeb.roteiro.Roteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
