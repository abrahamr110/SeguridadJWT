package com.es.jwtsecurity.controller;

import com.es.jwtsecurity.model.Producto;
import com.es.jwtsecurity.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getById(Long id) {
        return ResponseEntity.ok(productoService.getById(id));
    }

    @GetMapping("/productos/byNombre/{nombre}")
    public ResponseEntity<Producto> getByNombre(String nombre) {
        return ResponseEntity.ok(productoService.getByNombre(nombre));
    }

    @GetMapping("/productos/asc")
    public ResponseEntity<List<Producto>> getAllByAsc() {
        return ResponseEntity.ok(productoService.getAllByAsc());
    }
}
