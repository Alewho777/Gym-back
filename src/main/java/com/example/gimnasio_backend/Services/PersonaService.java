package com.example.gimnasio_backend.Services;

import java.time.LocalDate;
import java.util.List;

import com.example.gimnasio_backend.model.Persona;


public interface PersonaService {
	List<Persona> findAll();
	Persona save(Persona persona);
	Persona findById(Long id);
    void delete(Long id);
    void deleteByCedula(String cedula);
    Persona findByCedula(String cedula);
    List<Persona> findByFechaInscripcionBetween(LocalDate start, LocalDate end);
    void actualizarEstadosInscripcion();
}
