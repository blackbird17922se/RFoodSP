package com.dsd.rfoodsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{
    public Mesa findByNombre(String nombre);
    public Mesa findByIdMesa(Integer id);

}
