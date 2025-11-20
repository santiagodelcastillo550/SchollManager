package com.example.SchoolManager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SchoolManager.entities.Asignatura;
import com.example.SchoolManager.entities.Usuario;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long>{

	List<Asignatura> findByProfesor(Usuario profesor);
	
	Optional<Asignatura> findByNombre(String nombre);

}
