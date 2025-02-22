package com.example.gimnasio_backend.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gimnasio_backend.model.Persona;
import com.example.gimnasio_backend.model.Reporte;
import com.example.gimnasio_backend.model.ReporteDTO;
import com.example.gimnasio_backend.model.Ventas;
import com.example.gimnasio_backend.repository.ReporteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ReporteServiceImpl implements ReporteService{
	
	   private final ReporteRepository reporteRepository;
	    private final ObjectMapper objectMapper;
	    
	    @Autowired
	    private PersonaService personaService;
	    
	    @Autowired
	    private VentaService ventaService;

	    @Autowired
	    public ReporteServiceImpl(ReporteRepository reporteRepository, ObjectMapper objectMapper) {
	        this.reporteRepository = reporteRepository;
	        this.objectMapper = objectMapper;
	    }

	    //@Override
	    //@Transactional
	    //public Reporte crearReporte(ReporteDTO reporteDTO) {
	        // Validar campos
	        //if (reporteDTO.getTipo() == null || reporteDTO.getFecha() == null) {
	         //   throw new IllegalArgumentException("Tipo y fecha son requeridos");
	       // }
	        
	        //try {
	            //LocalDate[] rango = calcularRangoFechas(
	                //reporteDTO.getTipo(), 
	              //  reporteDTO.getFecha()
	            //);
	            
	            // Obtener datos filtrados
	            //List<Persona> personas = personaService.findByFechaInscripcionBetween(rango[0], rango[1]);
	            //List<Ventas> ventas = ventaService.findByFechaVentaBetween(rango[0], rango[1]);
	            
	            // Crear y guardar reporte
	            //Reporte reporte = new Reporte();
	            //reporte.setPersonas(objectMapper.writeValueAsString(personas));
	            //reporte.setVentas(objectMapper.writeValueAsString(ventas));
	           // reporte.setFiltrosAplicados(objectMapper.writeValueAsString(reporteDTO));
	            
	          //  return reporteRepository.save(reporte);
	        //} catch (JsonProcessingException e) {
	        //    throw new RuntimeException("Error al serializar datos del reporte", e);
	      //  }
	    //}
	    
	    @Override
	    @Transactional
	    public Reporte crearReporte(ReporteDTO reporteDTO) {
	        try {
	            Reporte reporte = new Reporte();
	            
	            // Asignar valores directamente desde el DTO
	            reporte.setPersonas(reporteDTO.getPersonas());
	            reporte.setVentas(reporteDTO.getVentas());
	            reporte.setFiltrosAplicados(reporteDTO.getFiltrosAplicados());
	            
	            return reporteRepository.save(reporte);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al guardar el reporte: " + e.getMessage());
	        }
	    }

	    private LocalDate[] calcularRangoFechas(String tipo, LocalDate fecha) {
	        return switch (tipo.toLowerCase()) {
	            case "dia" -> new LocalDate[]{fecha, fecha};
	            case "mes" -> new LocalDate[]{
	                fecha.withDayOfMonth(1),
	                fecha.withDayOfMonth(fecha.lengthOfMonth())
	            };
	            case "año" -> new LocalDate[]{
	                LocalDate.of(fecha.getYear(), 1, 1),
	                LocalDate.of(fecha.getYear(), 12, 31)
	            };
	            default -> throw new IllegalArgumentException("Tipo de reporte no válido");
	        };
	    }
	    
	    @Override
	    @Transactional
	    public List<Reporte> obtenerTodosReportes() {
	        return reporteRepository.findAll();
	    }

	    @Override
	    @Transactional
	    public Reporte obtenerReportePorId(Long id) {
	        return reporteRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
	    }
	    
	    @Override
	    @Transactional
	    public void eliminarReporte(Long id) {
	        Reporte reporte = reporteRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
	        reporte.setEstado(2);
	        reporteRepository.save(reporte);
	    }
	    
	    @Override
	    public Reporte actualizarReporte(Reporte reporte) {
	        return reporteRepository.save(reporte);
	    }
	    
	    @Override
	    public Reporte actualizarEstadoReporte(Long id, int estado) {
	        Reporte reporte = reporteRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
	        reporte.setEstado(estado);
	        return reporteRepository.save(reporte);
	    }
}
