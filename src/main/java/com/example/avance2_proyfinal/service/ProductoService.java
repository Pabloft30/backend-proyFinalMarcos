package com.example.avance2_proyfinal.service;

import com.example.avance2_proyfinal.model.Producto;
import com.example.avance2_proyfinal.model.Categoria;
import com.example.avance2_proyfinal.repository.ProductoRepository;
import com.example.avance2_proyfinal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<Producto> getProductoById(int id) {
        return productoRepository.findById(id);
    }

    // Registrar un nuevo producto
    public Producto addProducto(Producto producto) {
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto updateProducto(Integer id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(productoDetails.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setNombre(productoDetails.getNombre());
        producto.setCantidad(productoDetails.getCantidad());
        producto.setPrecio_dia(productoDetails.getPrecio_dia());
        producto.setPrecio_noche(productoDetails.getPrecio_noche());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setCategoria(categoria);
        producto.setFecha_ingreso(productoDetails.getFecha_ingreso());
        producto.setProveedor(productoDetails.getProveedor());

        return productoRepository.save(producto);
    }

    // Eliminar un producto por su ID
    public void deleteProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
    }
}
