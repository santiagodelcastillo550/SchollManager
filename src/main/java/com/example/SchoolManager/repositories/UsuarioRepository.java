package com.example.SchoolManager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SchoolManager.entities.Rol;
import com.example.SchoolManager.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
	long countByRol(Rol rol);
	
	List<Usuario> findByRol(Rol rol);
}
