package com.dsd.rfoodsp.repository;

import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.entities.Rol;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
// JpaRepository<Rol, Tipo dato llave primaria en la entidad>
public interface RolRepository extends JpaRepository<Rol, Integer>{
    public Rol findByNombre(String nombre);
 
}
