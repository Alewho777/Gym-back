package com.example.gimnasio_backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reportes")
@Data
public class Reporte implements Serializable{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String numeroInforme;
    
    @Column(nullable = false)
    private LocalDateTime fechaGeneracion;
    
    @Column(columnDefinition = "TEXT")
    private String personas; // JSON serializado
    
    @Column(columnDefinition = "TEXT")
    private String ventas; // JSON serializado
    
    @Column(columnDefinition = "TEXT")
    private String filtrosAplicados; // Para guardar los filtros usados
    
    @Column(nullable = false)
    private int estado = 1;
    
    @PrePersist
    protected void onCreate() {
        this.fechaGeneracion = LocalDateTime.now();
        this.numeroInforme = "REP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroInforme() {
		return numeroInforme;
	}

	public void setNumeroInforme(String numeroInforme) {
		this.numeroInforme = numeroInforme;
	}

	public LocalDateTime getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
    
    
    
}
