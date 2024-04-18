package com.example.myappbackend.controller;

import com.example.myappbackend.DAO.ProductoDAO;
import com.example.myappbackend.models.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ProductoController {
    @Autowired
    private ProductoDAO productoDAO;

    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productoDAO.findAll();
    }

    @PostMapping("/productos")
    public Producto registrarProducto(@RequestBody Producto producto) {
        return productoDAO.save(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        try {
            Producto producto = productoDAO.findById(id)
                    .orElseThrow(() -> new Exception("El producto con id: " + id + " no existe."));

            return ResponseEntity.ok(producto);
        }catch (Exception e){
            return ResponseEntity.of(Optional.empty());
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarContadorBusqueda(@PathVariable Long id) {
        try {
            Producto producto = productoDAO.findById(id)
                    .orElseThrow(() -> new Exception("El producto con id: " + id + " no existe."));

            int contador = producto.getContadorBusquedas();
            producto.setContadorBusquedas(++contador);

            Producto productoActualizado = productoDAO.save(producto);
            return ResponseEntity.ok(productoActualizado);
        }catch (Exception e){
            return ResponseEntity.of(Optional.empty());
        }
    }
}
