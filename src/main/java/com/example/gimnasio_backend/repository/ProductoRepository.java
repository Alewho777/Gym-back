package com.example.gimnasio_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gimnasio_backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Optional<Producto> findByCodigo(String codigo);
	Optional<Producto> deleteByCodigo(String codigo);
}
