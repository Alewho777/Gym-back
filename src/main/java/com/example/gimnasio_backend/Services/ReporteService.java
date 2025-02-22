package com.example.gimnasio_backend.Services;

import java.util.List;

import com.example.gimnasio_backend.model.Reporte;
import com.example.gimnasio_backend.model.ReporteDTO;

public interface ReporteService {
	Reporte crearReporte(ReporteDTO reporteDTO);
    List<Reporte> obtenerTodosReportes();
    Reporte obtenerReportePorId(Long id);
    void eliminarReporte(Long id);
    Reporte actualizarReporte(Reporte reporte);
    Reporte actualizarEstadoReporte(Long id, int estado);
}
