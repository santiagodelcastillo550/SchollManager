package com.example.SchoolManager.entities; 

import java.util.List; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.JoinColumn; 
import jakarta.persistence.JoinTable; 
import jakarta.persistence.ManyToMany; 
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.OneToMany; 

@Entity 
public class Asignatura { 
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	
	private String nombre; 
	private String descripcion; // Profesor que imparte la asignatura 
	
	@ManyToOne 
	@JoinColumn(name = "profesor_id") 
	private Usuario profesor; // Alumnos inscritos 
	
	@ManyToMany 
	@JoinTable( name = "asignaturas_alumnos", joinColumns = @JoinColumn(name = "asignatura_id"), 
	inverseJoinColumns = @JoinColumn(name = "alumno_id") ) 
	private List<Usuario> alumnos; // Relación con notas 
	
	@OneToMany(mappedBy = "asignatura") 
	private List<Nota> notas; // Relación con asistencias 
	
	@OneToMany(mappedBy = "asignatura") 
	private List<Asistencia> asistencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public List<Usuario> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	} 
	
	
}
