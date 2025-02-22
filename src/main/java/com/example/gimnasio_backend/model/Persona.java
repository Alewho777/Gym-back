package com.example.gimnasio_backend.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "personas", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "cedula")
	})
public class Persona implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private LocalDate fechaInscripcion;
    
    @Column(nullable = false)
    private LocalDate fecha_fin_Inscripcion;
    
    @Column(nullable = false)
    private int estado_inscripcion = 1;

    @Column(nullable = false)
    private int estado = 1;

    private double peso;
    
    @Column(nullable= false)
    private double suscripcion;
    
    @Column(nullable= false)
    private String genero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public double getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(double suscripcion) {
		this.suscripcion = suscripcion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getFecha_fin_Inscripcion() {
		return fecha_fin_Inscripcion;
	}

	public void setFecha_fin_Inscripcion(LocalDate fecha_fin_Inscripcion) {
		this.fecha_fin_Inscripcion = fecha_fin_Inscripcion;
	}

	public int getEstado_inscripcion() {
		return estado_inscripcion;
	}

	public void setEstado_inscripcion(int estado_inscripcion) {
		this.estado_inscripcion = estado_inscripcion;
	}
}
