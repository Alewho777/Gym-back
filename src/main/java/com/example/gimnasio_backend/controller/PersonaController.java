package com.example.gimnasio_backend.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.gimnasio_backend.Services.PersonaService;
import com.example.gimnasio_backend.model.Persona;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	 @Autowired
	    private PersonaService personaService;

	 //BUSCAR TODAS LAS PERSONAS
	  @GetMapping
	    public List<Persona> getAllPersonas() {
	        return personaService.findAll();
	    }

	  //BUSCAR PERSONAS POR ID
	    @GetMapping("/{id}")
	    public Persona getPersonaById(@PathVariable Long id) {
	        Persona persona = personaService.findById(id);
	        if (persona == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        return persona;
	    }

	    //BUSCAR PERSONAS POR CEDULA
	    @GetMapping("/findByCedula/{cedula}")
	    public Persona getPersonaByCedula(@PathVariable String cedula) {
	        Persona persona = personaService.findByCedula(cedula);
	        if (persona == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        return persona;
	    }

	    //CREAR PERSONAS
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Persona createPersona(@RequestBody Persona Persona) {
	        return personaService.save(Persona);
	    }

	    //ACTUALIZAR DATOS DE PERSONA POR ID
	    @PutMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void updatePersona(@RequestBody Persona persona, @PathVariable Long id) {
	        Persona updatePersona = personaService.findById(id);
	        if (updatePersona == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        //updatePersona.setCedula(Persona.getCedula());
	        updatePersona.setNombres(persona.getNombres());
	        updatePersona.setApellidos(persona.getApellidos());
	        updatePersona.setFechaInscripcion(persona.getFechaInscripcion());
	        updatePersona.setPeso(persona.getPeso());
	        updatePersona.setSuscripcion(persona.getSuscripcion());
	        updatePersona.setGenero(persona.getGenero());
	        updatePersona.setFecha_fin_Inscripcion(persona.getFecha_fin_Inscripcion());
	        updatePersona.setEstado_inscripcion(persona.getEstado_inscripcion());
	        
	        personaService.save(updatePersona);
	    }

	    //ACTUALIZAR DATOS DE PERSONA POR CEDULA
	    @PutMapping("/updateByCedula/{cedula}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void updatePersonaByCedula(@RequestBody Persona persona, @PathVariable String cedula) {
	        Persona updatePersonaByCedula = personaService.findByCedula(cedula);
	        if (updatePersonaByCedula == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        //updatePersonaByCedula.setCedula(persona.getCedula());
	        updatePersonaByCedula.setNombres(persona.getNombres());
	        updatePersonaByCedula.setApellidos(persona.getApellidos());
	        updatePersonaByCedula.setFechaInscripcion(persona.getFechaInscripcion());
	        updatePersonaByCedula.setPeso(persona.getPeso());
	        updatePersonaByCedula.setSuscripcion(persona.getSuscripcion());
	        updatePersonaByCedula.setGenero(persona.getGenero());
	        updatePersonaByCedula.setFecha_fin_Inscripcion(persona.getFecha_fin_Inscripcion());
	        updatePersonaByCedula.setEstado_inscripcion(persona.getEstado_inscripcion());
	        
	        persona.setCedula(cedula);
	        personaService.save(updatePersonaByCedula);
	    }

	    //BORRAR PERSONA POR ID
	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletePersona(@PathVariable Long id) {
	        Persona existingPersona = personaService.findById(id);
	        if (existingPersona == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        personaService.delete(id);
	    }
	    //BORRAR PERSONA POR CEDULA
	    @DeleteMapping("/deleteByCedula/{cedula}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deletePersonaByCedula(@PathVariable String cedula) {
	        Persona existingPersona = personaService.findByCedula(cedula);
	        if (existingPersona == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrado");
	        }
	        personaService.deleteByCedula(cedula);
	    }

	    //MANEJO DE EXCEPCIONES
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage()));
	        return errors;
	    }

	    //DESACTIVAR PERSONAS
	    @PutMapping("/desactivar/{cedula}")
	    public void desactivarPersona(@PathVariable String cedula) {
	        Persona persona = personaService.findByCedula(cedula);
	        persona.setEstado(2);
	        personaService.save(persona);
	    }
	    
	  //DESACTIVAR INSCRIPCION
	    @PutMapping("/desactivarInscripcion/{cedula}")
	    public void desactivarInscripcion(@PathVariable String cedula) {
	        Persona persona = personaService.findByCedula(cedula);
	        persona.setEstado_inscripcion(2);
	        personaService.save(persona);
	    }
	    
	    //FILTRAR POR FECHA
	    @GetMapping("/por-fecha")
	    public List<Persona> getPersonasPorFecha(
	        @RequestParam LocalDate start,
	        @RequestParam LocalDate end
	    ) {
	        return personaService.findByFechaInscripcionBetween(start, end);
	    }
	    
	    @PatchMapping("/actualizar-estados")
	    public ResponseEntity<String> actualizarEstadosInscripcion() {
	        personaService.actualizarEstadosInscripcion();
	        return ResponseEntity.ok("Estados actualizados correctamente");
	    }
}