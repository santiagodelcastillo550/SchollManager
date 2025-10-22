package com.example.SchoolManager.entities; 

import java.time.LocalDate;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.ManyToOne; 

@Entity 
public class Nota { 
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	
	private double valor; // Nota numérica 
	
	private LocalDate fecha; 
	
	@ManyToOne @JoinColumn(name = "alumno_id") 
	private Usuario alumno; 
	
	@ManyToOne @JoinColumn(name = "asignatura_id") 
	private Asignatura asignatura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Usuario getAlumno() {
		return alumno;
	}

	public void setAlumno(Usuario alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
