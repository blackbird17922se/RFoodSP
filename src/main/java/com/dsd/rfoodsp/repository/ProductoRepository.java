package com.dsd.rfoodsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    public Producto findByIdProducto(Integer id);
    public Producto findByNombre(String nombre);
    List<Producto> findByActivoTrue();
}
