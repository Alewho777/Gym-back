package com.example.gimnasio_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gimnasio_backend.Services.ReporteService;
import com.example.gimnasio_backend.model.Persona;
import com.example.gimnasio_backend.model.Reporte;
import com.example.gimnasio_backend.model.ReporteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

	private final ReporteService reporteService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReporteController(ReporteService reporteService, ObjectMapper objectMapper) {
        this.reporteService = reporteService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reporte crearReporte(@RequestBody ReporteDTO reporteDTO) {
        return reporteService.crearReporte(reporteDTO);
    }

    @GetMapping
    public List<Reporte> obtenerTodosReportes() {
        return reporteService.obtenerTodosReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReportePorId(@PathVariable Long id) {
        try {
            Reporte reporte = reporteService.obtenerReportePorId(id);
            
            // Deserializar los datos
            Map<String, Object> response = new HashMap<>();
            response.put("id", reporte.getId());
            response.put("numeroInforme", reporte.getNumeroInforme());
            response.put("fechaGeneracion", reporte.getFechaGeneracion());
            response.put("personas", objectMapper.readValue(reporte.getPersonas(), List.class));
            response.put("ventas", objectMapper.readValue(reporte.getVentas(), List.class));
            response.put("filtros", objectMapper.readValue(reporte.getFiltrosAplicados(), Map.class));
            
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el reporte");
        }
    }
    
    @PutMapping("/desactivar/{id}")
    public Reporte desactivarReporte(@PathVariable Long id) {
        Reporte reporte = reporteService.obtenerReportePorId(id);
        reporte.setEstado(2);
        return reporteService.actualizarReporte(reporte);
    }
    
    @PutMapping("/desactivarReporte/{id}")
    public Reporte desactivarReporteById(@PathVariable Long id) {
        return reporteService.actualizarEstadoReporte(id, 2);
    }
}
