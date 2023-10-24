package com.api.meuGuiaWeb.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmailAndSenha(String email, String senha);
}
