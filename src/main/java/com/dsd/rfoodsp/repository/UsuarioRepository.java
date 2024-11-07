package com.dsd.rfoodsp.repository;

import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public Usuario findByNomUsuario(String nomUsuario);
}
