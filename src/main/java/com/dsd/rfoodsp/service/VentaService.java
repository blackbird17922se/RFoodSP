package com.dsd.rfoodsp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dsd.rfoodsp.exception.EnumManejoErrores;
import com.dsd.rfoodsp.model.dto.DetalleOrdenDTO;
import com.dsd.rfoodsp.model.dto.OrdenDTO;
import com.dsd.rfoodsp.model.dto.VentaDTO;
import com.dsd.rfoodsp.model.entities.Orden;
import com.dsd.rfoodsp.model.entities.Usuario;
import com.dsd.rfoodsp.model.entities.Venta;
import com.dsd.rfoodsp.model.entities.VentaProducto;
import com.dsd.rfoodsp.repository.OrdenRepository;
import com.dsd.rfoodsp.repository.UsuarioRepository;
import com.dsd.rfoodsp.repository.VentaProductoRepository;
import com.dsd.rfoodsp.repository.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository repository;
    private final VentaProductoRepository ventaProductoRepository;
    private final UsuarioRepository usuarioRepository;
    private final OrdenRepository ordenRepository;

    private final OrdenService ordenService;
    private final ModelMapper mapper;

    
    public VentaService(VentaRepository repository, UsuarioRepository usuarioRepository,
            OrdenRepository ordenRepository, OrdenService ordenService, ModelMapper mapper,
            VentaProductoRepository ventaProductoRepository) {
        this.repository = repository;
        this.ventaProductoRepository = ventaProductoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ordenRepository = ordenRepository;
        this.ordenService = ordenService;
        this.mapper = mapper;
    }



    public VentaDTO setVenta(VentaDTO datos){

        Integer total = 0;

        Usuario usuario = usuarioRepository.findByIdUsuario(datos.getIdUsuario());
        if(usuario == null){
            throw new RuntimeException(EnumManejoErrores.USUARIO_NO_ENCONTRADO.getMensaje());
        }

        Orden orden = ordenRepository.findByIdOrden(datos.getIdOrden());
        if (orden == null) {
            throw new RuntimeException(EnumManejoErrores.ORDEN_NO_VALIDA.getMensaje());
        }

        List<DetalleOrdenDTO> listaDetalles = ordenService.getDetalleOrden(orden.getIdOrden());


        if (listaDetalles.isEmpty()) {
            throw new RuntimeException("La orden con ID " + datos.getIdOrden() + " no contiene productos.");
        }
        

        for(DetalleOrdenDTO detalle : listaDetalles){
            total += detalle.getPrecio() * detalle.getCantidad();
        }

        Venta venta = new Venta(LocalDateTime.now(), total, true, usuario, orden);

        /* Guardar la venta */
        Venta nuevaVenta = repository.save(venta);

        /* Agregar valores a tabla venta_producto */
        List<VentaProducto> listaVentaProductos = new ArrayList<>();
        
        for(DetalleOrdenDTO detalle : listaDetalles){

            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setPrecio(detalle.getPrecio());
            ventaProducto.setCantidad(detalle.getCantidad());
            ventaProducto.setSubtotal(detalle.getPrecio() * detalle.getCantidad());
            ventaProducto.setIdProducto(detalle.getIdProducto());
            ventaProducto.setIdVenta(nuevaVenta.getId_venta());

            listaVentaProductos.add(ventaProducto);

        }

        if (!listaVentaProductos.isEmpty()) {
            //saveAll: guardar una lista de entidades
            ventaProductoRepository.saveAll(listaVentaProductos);
        }

        return mapper.map(nuevaVenta, VentaDTO.class);
    }
}
