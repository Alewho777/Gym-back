package com.example.gimnasio_backend.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gimnasio_backend.model.Persona;
import com.example.gimnasio_backend.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService{

	@Autowired
    private PersonaRepository PersonaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return PersonaRepository.findAll();
    }

    @Override
    public Persona save(Persona persona) {
        return PersonaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long id) {
        return PersonaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        PersonaRepository.deleteById(id);
    }

	@Override
	public Persona findByCedula(String cedula) {
		return PersonaRepository.findByCedula(cedula).orElse(null);
	}

	@Override
	 public void deleteByCedula(String cedula) {
        PersonaRepository.deleteByCedula(cedula);
    }
	
	@Override
    public List<Persona> findByFechaInscripcionBetween(LocalDate start, LocalDate end) {
        return PersonaRepository.findByFechaInscripcionBetween(start, end);
    }
	
	@Override
	@Transactional
	public void actualizarEstadosInscripcion() {
	    List<Persona> personas = PersonaRepository.findAll();
	    
	    personas.forEach(persona -> {
	        int nuevoEstado = verificarEstadoInscripcion(persona.getFecha_fin_Inscripcion());
	        persona.setEstado_inscripcion(nuevoEstado);
	    });
	    
	    PersonaRepository.saveAll(personas);
	}

	private int verificarEstadoInscripcion(LocalDate fechaFin) {
	    return LocalDate.now().isAfter(fechaFin) ? 2 : 1;
	}

}
