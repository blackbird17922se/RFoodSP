package com.dsd.rfoodsp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.ProductoDTO;
import com.dsd.rfoodsp.model.entities.Producto;
import com.dsd.rfoodsp.repository.ProductoRepository;
import org.modelmapper.TypeToken;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final ModelMapper mapper;


    public ProductoService(ProductoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public ProductoDTO crearProducto(ProductoDTO datos){

        if(repository.findByNombre(datos.getNombre()) != null){
            throw new RuntimeException(EnumManejoErrores.PRODUCTO_DUPLICADO.getMensaje());
        }

        Producto producto = new Producto(datos.getNombre(), datos.getPrecio(), true);
        repository.save(producto);

        return mapper.map(producto, ProductoDTO.class);

    }


    public List<ProductoDTO> getProductos(){
        List<Producto> productos = repository.findByActivoTrue();

        // Usamos TypeToken para mapear la lista de entidades a una lista de DTOs
        List<ProductoDTO> productoDTO = mapper.map(productos, new TypeToken<List<ProductoDTO>>(){}.getType());

        return productoDTO;
    }


    public ProductoDTO editarProducto(Integer id, ProductoDTO datos){

        Producto productoEditar = verificarExistenciaProducto(id);
        productoEditar.setNombre(datos.getNombre());
        productoEditar.setPrecio(datos.getPrecio());

        repository.save(productoEditar);

        return mapper.map(productoEditar, ProductoDTO.class);
    }


    /** Desactivar o Activar un registro, es equivalente a borrar */
    public ProductoDTO cambiarEstadoProducto(Integer id){

        Producto productoEditar = verificarExistenciaProducto(id);

        // productoEditar.setActivo(productoEditar.isActivo() ? false : true);
        productoEditar.setActivo(!productoEditar.isActivo());

        repository.save(productoEditar);

        return mapper.map(productoEditar, ProductoDTO.class);
    }


    public Producto verificarExistenciaProducto(Integer id){
        
        Producto productoEditar = repository.findByIdProducto(id);

        if (productoEditar == null) {
            throw new RuntimeException(EnumManejoErrores.PRODUCTO_NO_ENCONTRADO.getMensaje());
        }

        return productoEditar;
    }
    
}
