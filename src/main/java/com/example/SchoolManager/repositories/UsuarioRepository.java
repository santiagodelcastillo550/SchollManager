package com.example.SchoolManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SchoolManager.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
