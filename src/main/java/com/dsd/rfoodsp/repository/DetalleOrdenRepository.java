package com.dsd.rfoodsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsd.rfoodsp.model.entities.DetalleOrden;
import com.dsd.rfoodsp.model.entities.Orden;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer>{

    // @Query("SELECT d FROM DetalleOrden d WHERE d.orden.idOrden = :idOrden")
    // List<DetalleOrden> findByOrdenId(@Param("idOrden") Integer idOrden);

    public List<DetalleOrden> findByOrden(Orden idOrden);
}
