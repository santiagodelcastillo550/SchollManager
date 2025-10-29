package com.example.SchoolManager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SchoolManager.entities.Nota;
import com.example.SchoolManager.entities.Usuario;

public interface NotaRepository extends JpaRepository<Nota, Long>{

	List<Nota> findByAlumno(Usuario alumno);
	Optional<Nota> findByAlumnoIdAndAsignaturaId(Long alumnoId, Long asignaturaId);
}
