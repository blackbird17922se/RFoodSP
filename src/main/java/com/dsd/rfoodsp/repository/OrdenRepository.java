package com.dsd.rfoodsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    
}
