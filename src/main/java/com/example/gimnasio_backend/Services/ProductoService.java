package com.example.gimnasio_backend.Services;

import java.util.List;

import com.example.gimnasio_backend.model.Producto;


public interface ProductoService {
	List<Producto> findAll();
	Producto save(Producto producto);
	Producto findById(Long id);
    void delete(Long id);
    void deleteByCodigo(String codigo);
    Producto findByCodigo(String codigo);
}
