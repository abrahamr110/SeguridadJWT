package com.es.jwtsecurity.service;

import com.es.jwtsecurity.model.Producto;
import com.es.jwtsecurity.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto getByNombre(String nombre) {
        return productoRepository.findByNombre(nombre).orElse(null);
    }

    public List<Producto> getAllByAsc() { return productoRepository.findAll(Sort.by(Sort.Direction.ASC,"nombre")); }

    public List<Producto> getAllByDesc() { return productoRepository.findAll(Sort.by(Sort.Direction.DESC,"nombre")); }


}
