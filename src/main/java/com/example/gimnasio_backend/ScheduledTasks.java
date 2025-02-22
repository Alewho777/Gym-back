package com.example.gimnasio_backend;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.gimnasio_backend.model.Persona;
import com.example.gimnasio_backend.repository.PersonaRepository;

@Service
public class ScheduledTasks {
	 private final PersonaRepository personaRepository;

	    public ScheduledTasks(PersonaRepository personaRepository) {
	        this.personaRepository = personaRepository;
	    }

	    @Scheduled(cron = "0 0 0 * * ?", zone = "America/Lima") // Ejecuta diario a media noche
	    public void actualizarEstadosInscripciones() {
	        List<Persona> personas = personaRepository.findAll();
	        
	        personas.forEach(persona -> {
	            LocalDate hoy = LocalDate.now();
	            if (hoy.isAfter(persona.getFecha_fin_Inscripcion())) {
	                persona.setEstado_inscripcion(2);
	            }
	        });
	        
	        personaRepository.saveAll(personas);
	    }
}
