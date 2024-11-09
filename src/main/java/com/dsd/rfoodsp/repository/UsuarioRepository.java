package com.dsd.rfoodsp.repository;

import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    // public Usuario findByNomUsuario(String nomUsuario);
    public Usuario findByIdUsuario(Integer idUsuario);
    Optional<Usuario> findByNomUsuario(String nomUsuario);
}
