package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(int id_producto) {
        return productoRepository.findById(id_producto).orElse(null);
    }

    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(int id_producto, Producto producto) {
        Producto prod = productoRepository.findById(producto.getId_producto()).orElse(null);
        if (prod != null) {
            prod.setNombre(producto.getNombre());
            prod.setCantidad(producto.getCantidad());
            prod.setPrecio_dia(producto.getPrecio_dia());
            prod.setPrecio_noche(producto.getPrecio_noche());
            prod.setDescripcion(producto.getDescripcion());
            prod.setCategoria(producto.getCategoria());
            prod.setFecha_ingreso(producto.getFecha_ingreso());
            prod.setProveedor(producto.getProveedor());
            return productoRepository.save(prod);
        }
        return null;
    }

    public void deleteProducto(int id_producto) {
        productoRepository.deleteById(id_producto);
    }

}
