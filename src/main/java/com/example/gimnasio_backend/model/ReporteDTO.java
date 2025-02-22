package com.example.gimnasio_backend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReporteDTO {
	@Column(nullable = false)
	private String tipo; // "dia", "mes" o "año"
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
	@JsonRawValue
	private String personas;
	//private List<Map<String, Object>> personas;
	
    //private List<Map<String, Object>> ventas;
	@JsonRawValue
    private String ventas;
    //private Map<String, Object> filtros;
	
	@JsonRawValue
    private String filtrosAplicados;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}

	public String getVentas() {
		return ventas;
	}

	public void setVentas(String ventas) {
		this.ventas = ventas;
	}

	public String getFiltrosAplicados() {
		return filtrosAplicados;
	}

	public void setFiltrosAplicados(String filtrosAplicados) {
		this.filtrosAplicados = filtrosAplicados;
	}
    
	
    
}
