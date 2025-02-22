package com.example.gimnasio_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.gimnasio_backend.model.Producto;
import com.example.gimnasio_backend.model.Ventas;

public interface VentaRepository extends JpaRepository<Ventas, Long>{
	List<Ventas> findByProducto(Producto producto);
	
	@Query("SELECT v FROM Ventas v WHERE v.fechaVenta BETWEEN :start AND :end")
	List<Ventas> findByFechaVentaBetween(
	    @Param("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
	    @Param("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
	);
}
