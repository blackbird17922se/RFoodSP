package com.dsd.rfoodsp.repository;

import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.Usuario;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    // public Usuario findByNomUsuario(String nomUsuario);

    @EntityGraph(attributePaths = {"rol"}) // traer los datos de la tabla rol
    public Usuario findByIdUsuario(Integer idUsuario);
    
    Optional<Usuario> findByNomUsuario(String nomUsuario);

    @EntityGraph(attributePaths = {"rol"}) // traer los datos de la tabla rol
    List<Usuario> findAll();

    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol")
    List<Usuario> findAllUsuarioConRoles();

}
