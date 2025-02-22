package com.example.gimnasio_backend.Services;

import java.time.LocalDate;
import java.util.List;

import com.example.gimnasio_backend.model.Producto;
import com.example.gimnasio_backend.model.Ventas;



public interface VentaService {
	List<Ventas> findAll();
	Ventas save(Ventas Ventas);
	Ventas findById(Long id);
    void delete(Long id);
    List <Ventas> findByProducto(Producto producto);
    List<Ventas> findByFechaVentaBetween(LocalDate start, LocalDate end);
}
