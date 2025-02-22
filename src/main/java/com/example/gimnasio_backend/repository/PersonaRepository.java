package com.example.gimnasio_backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.gimnasio_backend.model.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long> {
	 Optional<Persona> findByCedula(String cedula);
	 Optional<Persona> deleteByCedula(String cedula);
	 
	 @Query("SELECT p FROM Persona p WHERE p.fechaInscripcion BETWEEN :start AND :end")
	 List<Persona> findByFechaInscripcionBetween(
	     @Param("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
	     @Param("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
	 );
}
