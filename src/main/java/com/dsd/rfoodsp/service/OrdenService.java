package com.dsd.rfoodsp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.OrdenDTO;
import com.dsd.rfoodsp.model.entities.DetalleOrden;
import com.dsd.rfoodsp.model.entities.Mesa;
import com.dsd.rfoodsp.model.entities.Orden;
import com.dsd.rfoodsp.model.entities.Producto;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.repository.DetalleOrdenRepository;
import com.dsd.rfoodsp.repository.MesaRepository;
import com.dsd.rfoodsp.repository.OrdenRepository;
import com.dsd.rfoodsp.repository.ProductoRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;


@Service
public class OrdenService {

    private final OrdenRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;
    private final ModelMapper mapper;



    public OrdenService(OrdenRepository repository, UsuarioRepository usuarioRepository, 
    MesaRepository mesaRepository, ProductoRepository productoRepository, 
    DetalleOrdenRepository detalleOrdenRepository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
        this.productoRepository = productoRepository;
        this.detalleOrdenRepository = detalleOrdenRepository;
    }


    public OrdenDTO setOrden(OrdenDTO datos){

        Usuario usuario = usuarioRepository.findByIdUsuario(datos.getIdUsuario());
        if(usuario == null){
            throw new RuntimeException(EnumManejoErrores.USUARIO_NO_ENCONTRADO.getMensaje());
        }
        
        Mesa mesa = mesaRepository.findByIdMesa(datos.getIdMesa());
        if(mesa == null){
            throw new RuntimeException(EnumManejoErrores.MESA_NO_ENCONTRADA.getMensaje());
        }


        Orden orden = new Orden(false, false, datos.getObservaciones(),
         true, usuario, mesa);

        Orden nuevaOrden = repository.save(orden);


        // MAPEO Y CREACION OBJETO: Procesar los detalles
        List<DetalleOrden> detallesList = datos.getDetalles().stream()
        .map(detalleDTO -> {

            Producto producto = verificarExistenciaProducto(detalleDTO.getIdProducto());
            return new DetalleOrden(nuevaOrden, producto, detalleDTO.getCantidad(), producto.getPrecio());

        }).collect(Collectors.toList());

        detalleOrdenRepository.saveAll(detallesList);

        return mapper.map(nuevaOrden, OrdenDTO.class);
    }


    /** Obtiene las ordenes que aun no han pasado por el proceso de entrega al cliente */
    public List<OrdenDTO> getOrdenesActivas(){
        
        List<Orden> listaOrdenes = repository.findByEntregadoFalse();
        List<OrdenDTO> listaOrdenesDTO = mapper.map(listaOrdenes, new TypeToken<List<OrdenDTO>>(){}.getType());
        return listaOrdenesDTO;
    }


    /** Cambia el estado de la orden cuando el pedido fue entregado al cliente */
    public OrdenDTO cambiarEstadoEntregado(Integer id){
        Orden ordenCambiar = repository.findByIdOrden(id);

        if (ordenCambiar == null) {
            throw new RuntimeException(EnumManejoErrores.ORDEN_NO_VALIDA.getMensaje());
        }

        ordenCambiar.setEntregado(!ordenCambiar.isEntregado());

        repository.save(ordenCambiar);

        return mapper.map(ordenCambiar, OrdenDTO.class);
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////
    public Producto verificarExistenciaProducto(Integer id){
        
        Producto productoEditar = productoRepository.findByIdProducto(id);

        if (productoEditar == null) {
            throw new RuntimeException(EnumManejoErrores.PRODUCTO_NO_ENCONTRADO.getMensaje());
        }

        return productoEditar;
    }
 
}
