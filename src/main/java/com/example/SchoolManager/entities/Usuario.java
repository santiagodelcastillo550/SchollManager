package com.example.SchoolManager.entities; 

import java.util.List; 
import jakarta.persistence.Entity; 
import jakarta.persistence.EnumType; 
import jakarta.persistence.Enumerated; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.ManyToMany; 
import jakarta.persistence.OneToMany; 

@Entity 
public class Usuario { 
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	
	private String nombre; 
	private String apellido; 
	private String email; 
	private String password; 
	
	@Enumerated(EnumType.STRING) 
	private Rol rol; // ADMIN, PROFESOR, ALUMNO 
	
	// Relación con asignaturas como profesor 
	@OneToMany(mappedBy = "profesor") 
	private List<Asignatura> asignaturasImpartidas; 
	
	// Relación con asignaturas como alumno 
	@ManyToMany(mappedBy = "alumnos") 
	private List<Asignatura> asignaturasCursadas; 
	
	// Relación con notas 
	@OneToMany(mappedBy = "alumno") 
	private List<Nota> notas; 
	
	// Relación con asistencias 
	@OneToMany(mappedBy = "alumno") private List<Asistencia> asistencias;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Asignatura> getAsignaturasImpartidas() {
		return asignaturasImpartidas;
	}

	public void setAsignaturasImpartidas(List<Asignatura> asignaturasImpartidas) {
		this.asignaturasImpartidas = asignaturasImpartidas;
	}

	public List<Asignatura> getAsignaturasCursadas() {
		return asignaturasCursadas;
	}

	public void setAsignaturasCursadas(List<Asignatura> asignaturasCursadas) {
		this.asignaturasCursadas = asignaturasCursadas;
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
