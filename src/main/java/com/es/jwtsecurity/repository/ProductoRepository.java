package com.es.jwtsecurity.repository;

import com.es.jwtsecurity.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional <Producto> findByNombre(String name);
}
